package br.com.zitrus.view;

import java.io.PrintWriter;

import static br.com.zitrus.view.HTMLWriter.*;

public final class AuthorizationCreateHTMLWriter {
    public static void writeAuthorizationForm(final PrintWriter writer) {
        header(writer);
        writer.println("<form method=\"post\">");
        writer.println("<label for=\"procedure\">Procedimento:</label>");
        writer.println("<input type=\"number\" id=\"procedure\" name=\"procedure\" min=\"0\" required>");
        writer.println("<br>");
        writer.println("<label for=\"age\">Idade:</label>");
        writer.println("<input type=\"number\" id=\"age\" name=\"age\" min=\"0\" required>");
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
    public static void writeAuthorizationResponseAllowed(final PrintWriter writer,final int id, final String reason) {
        header(writer);
        h3(writer,"Aprovado");
        h4(writer,"Protocolo "+id+": "+reason);
        footer(writer);
    }
    public static void writeAuthorizationResponseReproved(final PrintWriter writer,final int id, final String reason) {
        header(writer);
        h3(writer,"Reprovado");
        h4(writer,"Protocolo "+id+": "+reason);
        footer(writer);
    }
    public static void writeAuthorizationResponseError(final PrintWriter writer) {
        header(writer);
        h3(writer,"Reprovado");
        h4(writer,"Houve um erro no tratamento da solicitação");
        footer(writer);
    }
}
