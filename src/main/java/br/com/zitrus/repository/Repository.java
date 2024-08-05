package br.com.zitrus.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;

final class Repository {
    static void connection(final Consumer<? super Connection> consumer) {
        try (final Connection connection = DriverManager.getConnection("jdbc:postgresql://postgres:5432/zitrus", "postgres", "un1m3d")) {
            consumer.accept(connection);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
