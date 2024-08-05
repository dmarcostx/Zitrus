package br.com.zitrus.view;

import br.com.zitrus.model.Authorization;

import java.io.PrintWriter;
import java.util.Collection;

import static br.com.zitrus.repository.AuthorizationRepository.PAGE_SIZE;
import static br.com.zitrus.view.HTMLWriter.*;

public final class AuthorizationQueryHTMLWriter {
    public static void writeAuthorizationQuery(final PrintWriter writer, final Collection<Authorization> authorizations, final byte page) {
        header(writer);
        h3(writer, "Página " + page);
        if(authorizations == null || authorizations.isEmpty()) {
            writer.println("Nenhum dado disponível");
            if(page>1)writer.println("<a href=?page="+(page-1)+">Página Anterior</a>");
        }
        else {
            writer.println("<table>");
            writer.println("<thead>");
            writer.println("<tr>");
            th(writer, "Identificador");
            th(writer, "Procedimento");
            th(writer, "Idade");
            th(writer, "Sexo");
            th(writer, "Permitido");
            th(writer, "Motivo");
            writer.println("</tr>");
            writer.println("</thead>");
            writer.println("<tbody>");
            authorizations.forEach(authorization -> {
                writer.println("<tr>");
                td(writer, authorization.getId());
                td(writer, authorization.getProcedure());
                td(writer, authorization.getAge());
                td(writer, authorization.isMale() ? "Masculino" : "Feminino");
                td(writer, authorization.isAllowed() ? "Sim" : "Não");
                td(writer, authorization.getReason());
                writer.println("</tr>");
            });
            writer.println("</tbody>");
            writer.println("</table>");
            writer.println("<div>");
            if(page>1)writer.println("<a href=?page="+(page-1)+">Página Anterior</a>");
            if(authorizations.size() == PAGE_SIZE) writer.println("<a href=?page="+(page+1)+">Próxima Página</a>");
            writer.println("</div>");
            writer.println("</table>");
        }
        footer(writer);
    }
}
