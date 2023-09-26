package leasing.app.contract;

import leasing.app.BaseTest;
import leasing.app.contract.dto.request.ContractUpdateDto;
import leasing.app.customer.Customer;
import leasing.app.vehicle.Vehicle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UpdateContractTest extends BaseTest {

    private static final String URL = "/contracts/{contractId}";

    @Test
    void testUpdateContractReturnsSuccess() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        Contract contract = dataCreator.createContract(vehicle, customer);

        Vehicle candidateVehicle = dataCreator.createVehicle();
        Customer candidateCustomer = dataCreator.createCustomer();

        ContractUpdateDto contractUpdateDto = new ContractUpdateDto()
            .setContractNumber(new BigDecimal(faker.number().numberBetween(100000, 999999)))
            .setMonthlyRate(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1500)))
            .setCustomerId(candidateCustomer.getId())
            .setVehicleId(candidateVehicle.getId());

        performAndPut(mockMvc, status().isOk(), contractUpdateDto, URL, contract.getId());

        Optional<Contract> contractRecord = contractRepository.findAll().stream().filter(e -> e.getId().equals(contract.getId())).findFirst();

        assertThat(contractRecord).isPresent();
        assertThat(contractRecord.get().getContractNumber()).isEqualTo(contractUpdateDto.getContractNumber());
        assertThat(contractRecord.get().getMonthlyRate()).isEqualTo(contractUpdateDto.getMonthlyRate());
        assertThat(contractRecord.get().getVehicle().getId()).isEqualTo(contractUpdateDto.getVehicleId());
        assertThat(contractRecord.get().getCustomer().getId()).isEqualTo(contractUpdateDto.getCustomerId());
    }

    @Test
    void testUpdateContractReturnsNotFound() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        Contract contract = dataCreator.createContract(vehicle, customer);

        Vehicle candidateVehicle = dataCreator.createVehicle();
        Customer candidateCustomer = dataCreator.createCustomer();

        ContractUpdateDto contractUpdateDto = new ContractUpdateDto()
            .setContractNumber(new BigDecimal(faker.number().numberBetween(100000, 999999)))
            .setMonthlyRate(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1500)))
            .setCustomerId(candidateCustomer.getId())
            .setVehicleId(candidateVehicle.getId());

        performAndPut(mockMvc, status().isNotFound(), contractUpdateDto, URL, UUID.randomUUID());

        Optional<Contract> contractRecord = contractRepository.findAll().stream().findFirst();

        assertThat(contractRecord).isPresent();
        assertThat(contractRecord.get().getContractNumber()).isEqualTo(contract.getContractNumber());
        assertThat(contractRecord.get().getMonthlyRate()).isEqualTo(contract.getMonthlyRate());
        assertThat(contractRecord.get().getVehicle().getId()).isEqualTo(contract.getVehicle().getId());
        assertThat(contractRecord.get().getCustomer().getId()).isEqualTo(contract.getCustomer().getId());
    }

    @Test
    void testUpdateContractReturnsPreconditionFailed() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        Contract contract = dataCreator.createContract(vehicle, customer);

        Vehicle candidateVehicle = dataCreator.createVehicle();
        Customer candidateCustomer = dataCreator.createCustomer();
        dataCreator.createContract(candidateVehicle, candidateCustomer);

        ContractUpdateDto contractUpdateDto = new ContractUpdateDto()
            .setContractNumber(new BigDecimal(faker.number().numberBetween(100000, 999999)))
            .setMonthlyRate(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1500)))
            .setCustomerId(candidateCustomer.getId())
            .setVehicleId(candidateVehicle.getId());

        performAndPut(mockMvc, status().isPreconditionFailed(), contractUpdateDto, URL, contract.getId());

        Optional<Contract> contractRecord = contractRepository.findAll().stream().filter(e -> e.getId().equals(contract.getId())).findFirst();

        assertThat(contractRecord).isPresent();
        assertThat(contractRecord.get().getContractNumber()).isEqualTo(contract.getContractNumber());
        assertThat(contractRecord.get().getMonthlyRate()).isEqualTo(contract.getMonthlyRate());
        assertThat(contractRecord.get().getVehicle().getId()).isEqualTo(contract.getVehicle().getId());
        assertThat(contractRecord.get().getCustomer().getId()).isEqualTo(contract.getCustomer().getId());
    }

    @Test
    void testUpdateContractReturnsNotFoundForVehicle() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        Contract contract = dataCreator.createContract(vehicle, customer);

        dataCreator.createVehicle();
        Customer candidateCustomer = dataCreator.createCustomer();

        ContractUpdateDto contractUpdateDto = new ContractUpdateDto()
            .setContractNumber(new BigDecimal(faker.number().numberBetween(100000, 999999)))
            .setMonthlyRate(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1500)))
            .setCustomerId(candidateCustomer.getId())
            .setVehicleId(UUID.randomUUID());

        performAndPut(mockMvc, status().isNotFound(), contractUpdateDto, URL, contract.getId());

        Optional<Contract> contractRecord = contractRepository.findAll().stream().findFirst();

        assertThat(contractRecord).isPresent();
        assertThat(contractRecord.get().getContractNumber()).isEqualTo(contract.getContractNumber());
        assertThat(contractRecord.get().getMonthlyRate()).isEqualTo(contract.getMonthlyRate());
        assertThat(contractRecord.get().getVehicle().getId()).isEqualTo(contract.getVehicle().getId());
        assertThat(contractRecord.get().getCustomer().getId()).isEqualTo(contract.getCustomer().getId());
    }

    @Test
    void testUpdateContractReturnsNotFoundForCustomer() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        Contract contract = dataCreator.createContract(vehicle, customer);

        Vehicle candidateVehicle = dataCreator.createVehicle();
        dataCreator.createCustomer();

        ContractUpdateDto contractUpdateDto = new ContractUpdateDto()
            .setContractNumber(new BigDecimal(faker.number().numberBetween(100000, 999999)))
            .setMonthlyRate(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1500)))
            .setCustomerId(UUID.randomUUID())
            .setVehicleId(candidateVehicle.getId());

        performAndPut(mockMvc, status().isNotFound(), contractUpdateDto, URL, contract.getId());

        Optional<Contract> contractRecord = contractRepository.findAll().stream().findFirst();

        assertThat(contractRecord).isPresent();
        assertThat(contractRecord.get().getContractNumber()).isEqualTo(contract.getContractNumber());
        assertThat(contractRecord.get().getMonthlyRate()).isEqualTo(contract.getMonthlyRate());
        assertThat(contractRecord.get().getVehicle().getId()).isEqualTo(contract.getVehicle().getId());
        assertThat(contractRecord.get().getCustomer().getId()).isEqualTo(contract.getCustomer().getId());
    }

    @Test
    void testUpdateContractReturnsBadRequest() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        Contract contract = dataCreator.createContract(vehicle, customer);

        Vehicle candidateVehicle = dataCreator.createVehicle();
        Customer candidateCustomer = dataCreator.createCustomer();

        ContractUpdateDto contractUpdateDto = new ContractUpdateDto()
            .setContractNumber(new BigDecimal(faker.number().numberBetween(100000, 999999)))
            .setMonthlyRate(null)
            .setCustomerId(candidateCustomer.getId())
            .setVehicleId(candidateVehicle.getId());

        performAndPut(mockMvc, status().isBadRequest(), contractUpdateDto, URL, contract.getId());

        Optional<Contract> contractRecord = contractRepository.findAll().stream().filter(e -> e.getId().equals(contract.getId())).findFirst();

        assertThat(contractRecord).isPresent();
        assertThat(contractRecord.get().getContractNumber()).isEqualTo(contract.getContractNumber());
        assertThat(contractRecord.get().getMonthlyRate()).isEqualTo(contract.getMonthlyRate());
        assertThat(contractRecord.get().getVehicle().getId()).isEqualTo(contract.getVehicle().getId());
        assertThat(contractRecord.get().getCustomer().getId()).isEqualTo(contract.getCustomer().getId());
    }
}