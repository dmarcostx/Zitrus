package br.com.zitrus.view;

import br.com.zitrus.model.Rule;

import java.io.PrintWriter;
import java.util.Collection;

import static br.com.zitrus.repository.AuthorizationRepository.PAGE_SIZE;
import static br.com.zitrus.view.HTMLWriter.*;

public final class RuleQueryHTMLWriter {
    public static void writeRuleQuery(final PrintWriter writer, final Collection<Rule> rules, final byte page) {
        header(writer);
        h3(writer, "Página " + page);
        if(rules == null || rules.isEmpty()) {
            writer.println("Nenhum dado disponível");
            if(page>1)writer.println("<a href=?page="+(page-1)+">Página Anterior</a>");
        }
        else {
            writer.println("<table>");
            writer.println("<thead>");
            writer.println("<tr>");
            th(writer, "Identificador");
            th(writer, "Procedimento");
            th(writer, "Idade Mínima");
            th(writer, "Idade Máxima");
            th(writer, "Sexo");
            th(writer, "Ativo");
            th(writer, "Ação");
            writer.println("</tr>");
            writer.println("</thead>");
            writer.println("<tbody>");
            rules.forEach(authorization -> {
                writer.println("<tr>");
                final int id = authorization.getId();
                td(writer, id);
                td(writer, authorization.getProcedure());
                td(writer, authorization.getAgeMin());
                td(writer, authorization.getAgeMax());
                td(writer, authorization.isMale() ? "Masculino" : "Feminino");
                writer.println("<td id=\"active-"+id+"\">"+(authorization.isActive() ? "Sim" : "Não")+"</td>");
                td(writer, authorization.isActive() ? "<a href=# class=\"disable\" rule-id=\""+ id +"\">Desativar</a>" : "<a href=# class=\"enable\" rule-id=\""+ id +"\">Ativar</a>");
                writer.println("</tr>");
            });
            writer.println("</tbody>");
            writer.println("</table>");
            writer.println("<div>");
            if(page>1)writer.println("<a href=?page="+(page-1)+">Página Anterior</a>");
            if(rules.size() == PAGE_SIZE) writer.println("<a href=?page="+(page+1)+">Próxima Página</a>");
            writer.println("</div>");
            writer.println("</table>");
            writer.println("<script src=\"/jquery-3.7.1.min.js\"></script>");
            writer.println("<script src=\"/rulequery.js\"></script>");
        }
        footer(writer);
    }
}
