package leasing.app.contract;

import leasing.app.contract.dto.request.ContractCreateDto;
import leasing.app.contract.dto.request.ContractUpdateDto;
import leasing.app.contract.dto.response.ContractGetDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/")
    public void createContract(@RequestBody ContractCreateDto contractCreateDto) {
        contractService.createContract(contractCreateDto);
    }

    @GetMapping("/{contractId}")
    public ContractGetDto getContract(@PathVariable UUID contractId) {
        return contractService.getContract(contractId);
    }

    @GetMapping("/")
    public List<ContractGetDto> getAllContracts(){
        return contractService.getAllContracts();
    }

    @DeleteMapping("/{contractId}")
    public void deleteContract(@PathVariable UUID contractId) {
        contractService.deleteContract(contractId);
    }

    @PutMapping("/{contractId}")
    public void updateContract(@PathVariable UUID contractId, @RequestBody ContractUpdateDto contractUpdateDto){
        contractService.updateContract(contractId, contractUpdateDto);
    }

}
