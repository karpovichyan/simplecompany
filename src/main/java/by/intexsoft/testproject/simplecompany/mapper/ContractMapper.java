package by.intexsoft.testproject.simplecompany.mapper;

import by.intexsoft.testproject.simplecompany.dto.ContractDto;
import by.intexsoft.testproject.simplecompany.entity.Contract;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    Contract toEntity(ContractDto contractDto);

    ContractDto toDto(Contract contract);
}
