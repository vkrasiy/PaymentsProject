package org.payments.repository.impl;

import org.payments.entities.Tariff;
import org.payments.repository.DBRepository;
import org.payments.repository.TariffRepository;
import org.payments.util.EntityExtractor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TariffRepositoryImpl extends DBRepository implements TariffRepository, EntityExtractor<Tariff> {
    @Override
    public Optional<Tariff> getTariffById(int id) {
        Tariff tariff = null;
        String query = "select id, tarrif_name, commission from tarrifs where id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                tariff = extractEntity(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.of(tariff);
    }

    @Override
    public List<Optional<Tariff>> getAllTariffs() {
        return null;
    }

    @Override
    public int getTariffByName(String name) {
        int tariffId = -1;
        String query = "select id from tarrifs where tarrif_name=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    tariffId = resultSet.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tariffId;
    }

    @Override
    public Tariff extractEntity(ResultSet resultSet) throws SQLException {
        Tariff tariff = null;
        while (resultSet.next()) {
            tariff = Tariff.newBuilder()
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2))
                    .setCommission(resultSet.getDouble(3))
                    .build();
        }
        return tariff;
    }
}
