package br.com.zitrus.service;

import br.com.zitrus.model.Response;
import br.com.zitrus.model.Rule;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.zitrus.repository.AuthorizationRepository.putAuthorization;
import static br.com.zitrus.repository.RulesRepository.getRules;
import static br.com.zitrus.view.AuthorizationCreateHTMLWriter.*;

@WebServlet("/Authorization/Create")
public final class AuthorizationCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (final PrintWriter writer = response.getWriter()) {
            writeAuthorizationForm(writer);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (final PrintWriter writer = response.getWriter()) {
            try {
                final Response allowed = isAllowed(Short.parseShort(request.getParameter("procedure")), Byte.parseByte(request.getParameter("age")), request.getParameter("sex").equals("male"));
                if (allowed.isAllowed())
                    writeAuthorizationResponseAllowed(writer, allowed.getId(), allowed.getReason());
                else writeAuthorizationResponseReproved(writer, allowed.getId(), allowed.getReason());
            } catch (final Exception ignored) {
                writeAuthorizationResponseError(writer);
            }
        }
    }

    private Response isAllowed(final short procedure, final byte age, final boolean male) {
        final Response response = new Response();
        final Collection<Rule> rules = getRules();
        Set<Rule> filtered = rules.stream().filter(r -> r.getProcedure() == procedure).collect(Collectors.toSet());
        final String reason;
        final int id;
        if (filtered.isEmpty()) {
            reason = "Procedimento não permitido";
            id = putAuthorization(procedure, age, male, false, reason);
            return response.setId(id).setAllowed(false).setReason(reason);
        } else {
            filtered = filtered.stream().filter(r -> r.isMale() == male).collect(Collectors.toSet());
            if (filtered.isEmpty()) {
                if (male) {
                    reason = "Sexo masculino não é permitido para esse procedimento";
                    id = putAuthorization(procedure, age, male, false, reason);
                    return response.setId(id).setAllowed(false).setReason(reason);
                } else {
                    reason = "Sexo feminino não é permitido para esse procedimento";
                    id = putAuthorization(procedure, age, male, false, reason);
                    return response.setId(id).setAllowed(false).setReason(reason);
                }
            } else {
                filtered = filtered.stream().filter(r -> r.getAgeMin() <= age && age <= r.getAgeMax()).collect(Collectors.toSet());
                if (filtered.isEmpty()) {
                    reason = "Idade não permitida para esse procedimento";
                    id = putAuthorization(procedure, age, male, false, reason);
                    return response.setId(id).setAllowed(false).setReason(reason);
                } else {
                    final StringBuilder builder = new StringBuilder();
                    builder.append("Regra");
                    builder.append(" ");
                    filtered.stream().map(Rule::getId).map(i -> i + "").reduce((a, b) -> a + ", " + b).ifPresent(builder::append);
                    reason = builder.toString();
                    id = putAuthorization(procedure, age, male, true, reason);
                    return response.setId(id).setAllowed(true).setReason(reason);
                }
            }
        }
    }
}