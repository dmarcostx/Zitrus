package br.com.zitrus.repository;

import br.com.zitrus.model.Rule;

import java.util.List;

public final class RulesCache {
    private static List<Rule> rules = List.of();
    private static boolean valid = false;
    public static void populateRulesCache(final List<Rule> rules) {
        RulesCache.rules = List.of(rules.toArray(Rule[]::new));
    }
    public static List<Rule> getRulesFromCache() {
        return rules;
    }

    public static boolean isValid() {
        return valid;
    }

    public static void validate() {
        valid = true;
    }
    public static void invalidate() {
        valid = false;
    }
}
