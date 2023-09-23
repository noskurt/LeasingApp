package leasing.app.contract;

import leasing.app.contract.dto.request.ContractCreateDto;
import leasing.app.contract.dto.request.ContractUpdateDto;
import leasing.app.contract.dto.response.ContractGetDto;
import leasing.app.customer.Customer;
import leasing.app.vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper {

    public Contract toContract(ContractCreateDto contractCreateDto, Vehicle vehicle, Customer customer) {
        return new Contract()
            .setContractNumber(contractCreateDto.getContractNumber())
            .setMonthlyRate(contractCreateDto.getMonthlyRate())
            .setVehicle(vehicle)
            .setCustomer(customer);
    }

    public Contract toContract(Contract contract, Vehicle vehicle, Customer customer, ContractUpdateDto contractUpdateDto) {
        return contract
            .setContractNumber(contractUpdateDto.getContractNumber())
            .setMonthlyRate(contractUpdateDto.getMonthlyRate())
            .setCustomer(customer)
            .setVehicle(vehicle);
    }

    public ContractGetDto toContractGetDto(Contract contract) {
        return new ContractGetDto()
            .setId(contract.getId())
            .setContractNumber(contract.getContractNumber())
            .setMonthlyRate(contract.getMonthlyRate())
            .setVehicle(toVehicleDto(contract.getVehicle()))
            .setCustomer(toCustomerDto(contract.getCustomer()));
    }

    private ContractGetDto.VehicleDto toVehicleDto(Vehicle vehicle) {
        return new ContractGetDto.VehicleDto()
            .setId(vehicle.getId())
            .setBrand(vehicle.getBrand())
            .setModel(vehicle.getModel())
            .setYear(vehicle.getYear())
            .setVin(vehicle.getVin())
            .setPrice(vehicle.getPrice());
    }

    private ContractGetDto.CustomerDto toCustomerDto(Customer customer) {
        return new ContractGetDto.CustomerDto()
            .setId(customer.getId())
            .setFirstName(customer.getFirstName())
            .setLastName(customer.getLastName())
            .setBirthdate(customer.getBirthdate());
    }
}
