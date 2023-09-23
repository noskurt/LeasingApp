package leasing.app.contract;

import leasing.app.contract.dto.request.ContractCreateDto;
import leasing.app.contract.dto.request.ContractUpdateDto;
import leasing.app.contract.dto.response.ContractGetDto;

import java.util.List;
import java.util.UUID;

public interface ContractService {
    void createContract(ContractCreateDto contractCreateDto);
    ContractGetDto getContract(UUID contractId);
    List<ContractGetDto> getAllContracts();
    void deleteContract(UUID contractId);
    void updateContract(UUID contractIdm, ContractUpdateDto contractUpdateDto);
}
