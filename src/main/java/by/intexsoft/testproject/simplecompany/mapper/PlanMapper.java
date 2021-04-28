package by.intexsoft.testproject.simplecompany.mapper;

import by.intexsoft.testproject.simplecompany.dto.PlanDto;
import by.intexsoft.testproject.simplecompany.entity.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PlanMapper {
    @Mapping(target = "id", ignore = true)
    Plan toEntity(PlanDto planDto);

    PlanDto toDto(Plan plan);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(PlanDto planDto, @MappingTarget Plan plan);
}
