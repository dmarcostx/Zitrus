package br.com.zitrus.service;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static br.com.zitrus.repository.AuthorizationRepository.getAuthorizations;
import static br.com.zitrus.view.AuthorizationQueryHTMLWriter.writeAuthorizationQuery;

@WebServlet("/Authorization/Query")
public final class AuthorizationQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        final byte page = request.getParameterMap().containsKey("page")?Byte.parseByte(request.getParameter("page")) : 1;
        try (final PrintWriter writer = response.getWriter()) {
            writeAuthorizationQuery(writer,                    getAuthorizations(page), page);
        }
    }
}