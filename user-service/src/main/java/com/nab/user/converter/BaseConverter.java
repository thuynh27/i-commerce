package com.nab.user.converter;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class BaseConverter < T, C >{
    private final Function< T,
        C > fromDto;
    private final Function < C,
        T > fromEntity;

    /**
     * @param fromDto
     *            Function that converts given dto entity into the domain
     *            entity.
     * @param fromEntity
     *            Function that converts given domain entity into the dto
     *            entity.
     */
    public BaseConverter(final Function < T, C > fromDto, final Function < C, T > fromEntity) {
        this.fromDto = fromDto;
        this.fromEntity = fromEntity;
    }

    /**
     * @param dtoEnitty
     *            DTO entity
     * @return The domain representation - the result of the converting function
     *         application on dto entity.
     */
    public final C convertFromDto(final T dtoEnitty) {
        return fromDto.apply(dtoEnitty);
    }

    /**
     * @param domainEntity
     *            domain entity
     * @return The DTO representation - the result of the converting function
     *         application on domain entity.
     */
    public final T convertFromEntity(final C domainEntity) {
        return fromEntity.apply(domainEntity);
    }

    /**
     * @param dtoEntityCollection
     *            collection of DTO entities
     * @return List of domain representation of provided entities retrieved by
     *         mapping each of them with the conversion function
     */
    public final List< C > createFromDtos(final Collection< T > dtoEntityCollection) {
        return dtoEntityCollection.stream().map(this::convertFromDto).collect(Collectors.toList());
    }

    /**
     * @param domainEnityCollection
     *            collection of domain entities
     * @return List of domain representation of provided entities retrieved by
     *         mapping each of them with the conversion function
     */
    public final List < T > createFromEntities(final Collection < C > domainEnityCollection) {
        return domainEnityCollection.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }
}
