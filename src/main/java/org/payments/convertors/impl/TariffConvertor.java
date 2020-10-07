package org.payments.convertors.impl;

import org.payments.convertors.AbstractConvertor;
import org.payments.dtos.impl.TariffDTO;
import org.payments.entities.Tariff;

public class TariffConvertor extends AbstractConvertor<Tariff, TariffDTO> {

    public TariffConvertor(Class<Tariff> tariffClass, Class<TariffDTO> tariffDTOClass) {
        super(tariffClass, tariffDTOClass);
    }

    @Override
    public Tariff toEntity(TariffDTO tariffDTO) {
        return Tariff.newBuilder()
                .setCommission(tariffDTO.getCommission())
                .setId(tariffDTO.getId())
                .setName(tariffDTO.getName())
                .build();
    }

    @Override
    public TariffDTO toDto(Tariff tariff) {
        return TariffDTO.newBuilder()
                .setCommission(tariff.getCommission())
                .setId(tariff.getId())
                .setName(tariff.getName())
                .build();
    }
}
