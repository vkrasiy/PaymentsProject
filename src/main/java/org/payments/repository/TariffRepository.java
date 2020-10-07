package org.payments.repository;

import org.payments.entities.Tariff;

import java.util.List;
import java.util.Optional;

public interface TariffRepository {
    Optional<Tariff> getTariffById(int id);
    List<Optional<Tariff>> getAllTariffs();
    int getTariffByName(String name);
}
