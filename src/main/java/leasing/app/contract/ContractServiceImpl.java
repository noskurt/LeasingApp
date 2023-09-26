package leasing.app.contract;

import leasing.app.contract.dto.request.ContractCreateDto;
import leasing.app.contract.dto.request.ContractUpdateDto;
import leasing.app.contract.dto.response.ContractGetDto;
import leasing.app.contract.exception.ContractNotFoundException;
import leasing.app.contract.exception.VehicleAlreadyAssignedToContractException;
import leasing.app.customer.Customer;
import leasing.app.customer.CustomerRepository;
import leasing.app.customer.exception.CustomerNotFoundException;
import leasing.app.vehicle.Vehicle;
import leasing.app.vehicle.VehicleRepository;
import leasing.app.vehicle.exception.VehicleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final ContractMapper contractMapper;

    @Override
    public void createContract(ContractCreateDto contractCreateDto) {
        checkVehicleAssignment(contractCreateDto.getVehicleId());

        Vehicle vehicle = vehicleRepository.findById(contractCreateDto.getVehicleId()).orElseThrow(() -> new VehicleNotFoundException(contractCreateDto.getVehicleId()));
        Customer customer = customerRepository.findById(contractCreateDto.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException(contractCreateDto.getCustomerId()));

        Contract contract = contractMapper.toContract(contractCreateDto, vehicle, customer);
        contractRepository.save(contract);
    }

    @Override
    public ContractGetDto getContract(UUID contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new ContractNotFoundException(contractId));
        return contractMapper.toContractGetDto(contract);
    }

    @Override
    public List<ContractGetDto> getAllContracts() {
        List<Contract> allContracts = contractRepository.findAll();
        return allContracts.stream().map(contractMapper::toContractGetDto).toList();
    }

    @Override
    public void deleteContract(UUID contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new ContractNotFoundException(contractId));
        contractRepository.delete(contract);
    }

    @Override
    public void updateContract(UUID contractId, ContractUpdateDto contractUpdateDto) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new ContractNotFoundException(contractId));

        if (contract.getVehicle().getId() != contractUpdateDto.getVehicleId()) {
            checkVehicleAssignment(contractUpdateDto.getVehicleId());
        }

        Vehicle vehicle = vehicleRepository.findById(contractUpdateDto.getVehicleId()).orElseThrow(() -> new VehicleNotFoundException(contractUpdateDto.getVehicleId()));
        Customer customer = customerRepository.findById(contractUpdateDto.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException(contractUpdateDto.getCustomerId()));

        Contract updatedContract = contractMapper.toContract(contract, vehicle, customer, contractUpdateDto);
        contractRepository.save(updatedContract);
    }

    private void checkVehicleAssignment(UUID vehicleId) {
        boolean isVehicleAssigned = contractRepository.existsByVehicleId(vehicleId);
        if (isVehicleAssigned) throw new VehicleAlreadyAssignedToContractException(vehicleId);
    }
}
