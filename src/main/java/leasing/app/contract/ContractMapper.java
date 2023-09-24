package leasing.app.contract;

import leasing.app.contract.dto.request.ContractCreateDto;
import leasing.app.contract.dto.request.ContractUpdateDto;
import leasing.app.contract.dto.response.ContractGetDto;
import leasing.app.customer.Customer;
import leasing.app.vehicle.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContractMapper {

    Contract toContract(ContractCreateDto contractCreateDto, Vehicle vehicle, Customer customer);

    Contract toContract(@MappingTarget Contract contract, Vehicle vehicle, Customer customer, ContractUpdateDto contractUpdateDto);

    ContractGetDto toContractGetDto(Contract contract);
}
