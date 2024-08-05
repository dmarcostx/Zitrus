package br.com.zitrus.view;

import java.io.PrintWriter;

import static br.com.zitrus.view.HTMLWriter.*;

public final class RuleCreateHTMLWriter {
    public static void writeRuleForm(final PrintWriter writer) {
        header(writer, "Cadastro de Regra para Autorização de Procedimento Médico");
        writer.println("<form method=\"post\">");
        writer.println("<label for=\"procedure\">Procedimento:</label>");
        writer.println("<input type=\"number\" id=\"procedure\" name=\"procedure\" min=\"0\" required>");
        writer.println("<br>");
        writer.println("<label for=\"age-min\">Idade mínima:</label>");
        writer.println("<input type=\"number\" id=\"age-min\" name=\"age-min\" min=\"0\" required>");
        writer.println("<br>");
        writer.println("<label for=\"age-max\">Idade máxima:</label>");
        writer.println("<input type=\"number\" id=\"age-max\" name=\"age-max\" min=\"0\" required>");
        writer.println("<br>");
        writer.println("<label for=\"sex\">Sexo:</label>");
        writer.println("<div>");
        writer.println("<input type=\"radio\" id=\"male\" name=\"sex\" value=\"male\" required>");
        writer.println("<label for=\"male\">Masculino</label>");
        writer.println("</div>");
        writer.println("<div>");
        writer.println("<input type=\"radio\" id=\"female\" name=\"sex\" value=\"female\" required>");
        writer.println("<label for=\"female\">Feminino</label>");
        writer.println("</div>");
        writer.println("<br>");
        writer.println("<input type=\"submit\" value=\"Enviar\">");
        writer.println("</form>");
        footer(writer);
    }

    public static void writeRuleCreated(final PrintWriter writer) {
        header(writer);
        h3(writer, "Regra cadastrada");
        h4(writer, "<a href=\"/Rule/Query\">Consultar regras</a>");
        footer(writer);
    }

    public static void writeRuleCreateError(final PrintWriter writer) {
        header(writer);
        h3(writer, "Erro ao cadastrar regra");
        h4(writer, "<a href=\"/Rule/Create\">Voltar ao cadastro</a>");
        footer(writer);
    }
}
