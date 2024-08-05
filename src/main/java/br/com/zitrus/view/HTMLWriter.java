package br.com.zitrus.view;

import java.io.PrintWriter;

final class HTMLWriter {
    static void h1(final PrintWriter writer,final String string) {
        writer.println("<h1>"+string+"</h1>");
    }
    static void h2(final PrintWriter writer,final String string) {
        writer.println("<h2>"+string+"</h2>");
    }
    static void h3(final PrintWriter writer,final String string) {
        writer.println("<h3>"+string+"</h3>");
    }
    static void h4(final PrintWriter writer,final String string) {
        writer.println("<h4>"+string+"</h4>");
    }
    static void th(final PrintWriter writer,final String string) {
        writer.println("<th>"+string+"</th>");
    }
    static void td(final PrintWriter writer,final Object string) {
        writer.println("<td>"+string+"</td>");
    }
    static void header(final PrintWriter writer,final String title) {
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<meta charset=\"UTF-8\">");
        writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/style.css\">");
        writer.println("<title>Zitrus</title>");
        writer.println("</head>");
        writer.println("<body>");
        h1(writer, title);
        h2(writer, "UNIMED");
    }
    static void header(final PrintWriter writer) {
        header(writer,"Autorização de Procedimento Médico");
    }

    static void footer(final PrintWriter writer) {
        writer.println("<footer><a href=/>Voltar ao Menu Principal</a></footer>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
