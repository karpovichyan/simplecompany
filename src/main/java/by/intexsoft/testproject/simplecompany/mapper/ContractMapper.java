package by.intexsoft.testproject.simplecompany.mapper;

import by.intexsoft.testproject.simplecompany.dto.ContractDto;
import by.intexsoft.testproject.simplecompany.entity.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    @Mapping(target = "id", ignore = true)
    Contract toEntity(ContractDto contractDto);

    ContractDto toDto(Contract contract);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(ContractDto contractDto, @MappingTarget Contract contract);
}
