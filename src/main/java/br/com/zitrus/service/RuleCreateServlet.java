package br.com.zitrus.service;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static br.com.zitrus.repository.RulesRepository.createRule;
import static br.com.zitrus.view.RuleCreateHTMLWriter.*;

@WebServlet("/Rule/Create")
public final class RuleCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (final PrintWriter writer = response.getWriter()) {
            writeRuleForm(writer);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (final PrintWriter writer = response.getWriter()) {
            try {
                createRule(Short.parseShort(request.getParameter("procedure")),Byte.parseByte(request.getParameter("age-min")),Byte.parseByte(request.getParameter("age-max")),request.getParameter("sex").equals("male"));
                writeRuleCreated(writer);
            } catch (final Exception ignored) {
                ignored.printStackTrace();
                writeRuleCreateError(writer);
            }
        }
    }
}