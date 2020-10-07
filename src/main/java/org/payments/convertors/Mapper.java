package org.payments.convertors;

public interface Mapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

}
