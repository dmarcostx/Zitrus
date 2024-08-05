package br.com.zitrus.repository;

import br.com.zitrus.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static br.com.zitrus.repository.Repository.connection;
import static br.com.zitrus.repository.RulesCache.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public final class RulesRepository {
    private static final byte PAGE_SIZE = 10;

    private static List<Rule> getRulesFromDatabase() {
        final ArrayList<Rule> rules = new ArrayList<>();
        connection(connection -> {
            try (final Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM rules")) {
                while (resultSet.next()) {
                    final Rule rule = new Rule();
                    rule.setId(resultSet.getInt("id"));
                    rule.setProcedure(resultSet.getShort("procedure"));
                    rule.setAgeMin(resultSet.getByte("ageMin"));
                    rule.setAgeMax(resultSet.getByte("ageMax"));
                    rule.setMale(resultSet.getBoolean("male"));
                    rule.setActive(resultSet.getBoolean("active"));
                    rules.add(rule);
                }
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return rules;
    }

    public static List<Rule> getRules() {
        if (RulesCache.isValid()) return getRulesFromCache();
        else {
            final List<Rule> rules = getRulesFromDatabase();
            populateRulesCache(rules);
            RulesCache.validate();
            return rules;
        }
    }

    public static List<Rule> getRules(final byte page) {
        final List<Rule> rules = getRules();
        final int size = rules.size();
        return rules.subList((min(max(page * PAGE_SIZE - PAGE_SIZE, 0), size)), min((page + 1) * PAGE_SIZE, size));
    }

    public static void createRule(final short procedure, final byte ageMin, final byte ageMax, final boolean male) {
        invalidate();
        connection(connection -> {
            try (final Statement statement = connection.createStatement()) {
                statement.execute("INSERT INTO rules (procedure,ageMin,ageMax,male) VALUES (" + procedure + "," + min(ageMin, ageMax) + "," + max(ageMin, ageMax) + "," + male + ")");
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void setRuleActive(final int id, final boolean active) {
        invalidate();
        connection(connection -> {
            try (final Statement statement = connection.createStatement()) {
                statement.execute("UPDATE rules SET active = " + active + " WHERE id = " + id);
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
