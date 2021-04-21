package by.intexsoft.testproject.simplecompany.mapper;

import by.intexsoft.testproject.simplecompany.dto.PositionDto;
import by.intexsoft.testproject.simplecompany.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    @Mapping(target = "id", ignore = true)
    Position toEntity(PositionDto positionDto);

    PositionDto toDto(Position position);

    @Mapping(target = "position.id", ignore = true)
    void updateFromDto(PositionDto positionDto, @MappingTarget Position position);
}
