package br.com.zitrus.repository;

import br.com.zitrus.model.Authorization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static br.com.zitrus.repository.Repository.connection;

public final class AuthorizationRepository {
    public static final byte PAGE_SIZE = 10;

    public static List<Authorization> getAuthorizations(final byte page) {
        final ArrayList<Authorization> authorizations = new ArrayList<>();
        connection(connection -> {
            try (final Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM authorizations ORDER BY id LIMIT "+PAGE_SIZE+" OFFSET "+ (Math.max(page*PAGE_SIZE - PAGE_SIZE, 0)))) {
                while (resultSet.next()) {
                    final Authorization authorization = new Authorization();
                    authorization.setId(resultSet.getInt("id"));
                    authorization.setProcedure(resultSet.getShort("procedure"));
                    authorization.setAge(resultSet.getByte("age"));
                    authorization.setMale(resultSet.getBoolean("male"));
                    authorization.setAllowed(resultSet.getBoolean("allowed"));
                    authorization.setReason(resultSet.getString("reason"));
                    authorizations.add(authorization);
                }
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return authorizations;
    }
    public static int putAuthorization(final short procedure, final byte age, final boolean male,final boolean allowed,final String reason) {
        final int[] id = {0};
        connection(connection -> {
            try (final PreparedStatement statement = connection.prepareStatement("INSERT INTO authorizations (procedure, age,male,allowed,reason) VALUES (?,?,?,?,?) RETURNING id")) {
               statement.setShort(1,procedure);
               statement.setByte(2,age);
               statement.setBoolean(3,male);
               statement.setBoolean(4,allowed);
               statement.setString(5,reason);
               final ResultSet resultSet = statement.executeQuery();
               if (resultSet.next()) id[0] = resultSet.getInt("id");
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return id[0];
    }
}
