package br.com.zitrus.service;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static br.com.zitrus.repository.RulesRepository.getRules;
import static br.com.zitrus.repository.RulesRepository.setRuleActive;
import static br.com.zitrus.view.RuleQueryHTMLWriter.writeRuleQuery;

@WebServlet("/Rule/Query")
public final class RuleQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        final byte page = request.getParameterMap().containsKey("page")?Byte.parseByte(request.getParameter("page")) : 1;
        try (final PrintWriter writer = response.getWriter()) {
            writeRuleQuery(writer,                    getRules(page), page);
        }
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setRuleActive(Integer.parseInt(request.getParameter("id")),Boolean.parseBoolean(request.getParameter("active")));
    }
}