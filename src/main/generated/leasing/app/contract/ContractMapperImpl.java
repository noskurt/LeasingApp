package leasing.app.contract;

import javax.annotation.processing.Generated;
import leasing.app.contract.dto.request.ContractCreateDto;
import leasing.app.contract.dto.request.ContractUpdateDto;
import leasing.app.contract.dto.response.ContractGetDto;
import leasing.app.customer.Customer;
import leasing.app.vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-25T16:48:31+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ContractMapperImpl implements ContractMapper {

    @Override
    public Contract toContract(ContractCreateDto contractCreateDto, Vehicle vehicle, Customer customer) {
        if ( contractCreateDto == null && vehicle == null && customer == null ) {
            return null;
        }

        Contract contract = new Contract();

        if ( contractCreateDto != null ) {
            contract.setContractNumber( contractCreateDto.getContractNumber() );
            contract.setMonthlyRate( contractCreateDto.getMonthlyRate() );
        }
        contract.setVehicle( vehicle );
        contract.setCustomer( customer );

        return contract;
    }

    @Override
    public Contract toContract(Contract contract, Vehicle vehicle, Customer customer, ContractUpdateDto contractUpdateDto) {
        if ( vehicle == null && customer == null && contractUpdateDto == null ) {
            return contract;
        }

        if ( contractUpdateDto != null ) {
            contract.setContractNumber( contractUpdateDto.getContractNumber() );
            contract.setMonthlyRate( contractUpdateDto.getMonthlyRate() );
        }
        contract.setVehicle( vehicle );
        contract.setCustomer( customer );

        return contract;
    }

    @Override
    public ContractGetDto toContractGetDto(Contract contract) {
        if ( contract == null ) {
            return null;
        }

        ContractGetDto contractGetDto = new ContractGetDto();

        contractGetDto.setId( contract.getId() );
        contractGetDto.setContractNumber( contract.getContractNumber() );
        contractGetDto.setMonthlyRate( contract.getMonthlyRate() );
        contractGetDto.setVehicle( vehicleToVehicleDto( contract.getVehicle() ) );
        contractGetDto.setCustomer( customerToCustomerDto( contract.getCustomer() ) );

        return contractGetDto;
    }

    protected ContractGetDto.VehicleDto vehicleToVehicleDto(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        ContractGetDto.VehicleDto vehicleDto = new ContractGetDto.VehicleDto();

        vehicleDto.setId( vehicle.getId() );
        vehicleDto.setBrand( vehicle.getBrand() );
        vehicleDto.setModel( vehicle.getModel() );
        vehicleDto.setYear( vehicle.getYear() );
        vehicleDto.setVin( vehicle.getVin() );
        vehicleDto.setPrice( vehicle.getPrice() );

        return vehicleDto;
    }

    protected ContractGetDto.CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        ContractGetDto.CustomerDto customerDto = new ContractGetDto.CustomerDto();

        customerDto.setId( customer.getId() );
        customerDto.setFirstName( customer.getFirstName() );
        customerDto.setLastName( customer.getLastName() );
        customerDto.setBirthdate( customer.getBirthdate() );

        return customerDto;
    }
}
