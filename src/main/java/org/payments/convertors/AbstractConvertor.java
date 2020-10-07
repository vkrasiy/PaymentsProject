package org.payments.convertors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public abstract class AbstractConvertor<Entity, Dto> implements Mapper<Entity, Dto> {


    protected ModelMapper mapper ;
    private Class<Entity> entityClass;
    private Class<Dto> dtoClass;

    public AbstractConvertor(Class<Entity> entityClass, Class<Dto> dtoClass) {
        mapper = modelMapper();
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

//    public Converter<Dto, Entity> toEntityConverter() {
//        return context -> {
//            Dto source = context.getSource();
//            Entity destination = context.getDestination();
//            convertSpecificFieldsToEntity(source, destination);
//            return context.getDestination();
//        };
//    }

//    public Converter<Entity, Dto> toDtoConverter() {
//        return context -> {
//            Unicorn source = context.getSource();
//            UnicornDto destination = context.getDestination();
//            mapSpecificFields(source, destination);
//            return context.getDestination();
//        };
//    }

    private ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setDeepCopyEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

    @Override
    public Entity toEntity(Dto dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public Dto toDto(Entity entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }


    public List<Dto> toDto(List<Entity> entities) {
        return Objects.isNull(entities)
                ? null
                : entities.stream().map(this::toDto).collect(Collectors.toList());
    }


    public List<Entity> toEntity(List<Dto> dtos) {
        return Objects.isNull(dtos)
                ? null
                : dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public void convertSpecificFieldsToEntity(Dto source, Entity destination) {
    }

    public void convertSpecificFieldsToDto(Entity source, Dto destination) {
    }
}
