package leasing.app.contract;

import leasing.app.BaseTest;
import leasing.app.contract.dto.request.ContractCreateDto;
import leasing.app.customer.Customer;
import leasing.app.customer.dto.request.CustomerCreateDto;
import leasing.app.vehicle.Vehicle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateContractTest extends BaseTest {

    @Test
    void testCreateContractReturnsSuccess() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();

        ContractCreateDto contractCreateDto = new ContractCreateDto()
            .setContractNumber(new BigDecimal(faker.number().numberBetween(100000, 999999)))
            .setMonthlyRate(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1500)))
            .setCustomerId(customer.getId())
            .setVehicleId(vehicle.getId());

        performAndPost(mockMvc, status().isOk(), contractCreateDto, "/contracts");

        Optional<Contract> contract = contractRepository.findAll().stream().findFirst();

        assertThat(contract).isPresent();
        assertThat(contract.get().getContractNumber()).isEqualTo(contractCreateDto.getContractNumber());
        assertThat(contract.get().getMonthlyRate()).isEqualTo(contractCreateDto.getMonthlyRate());
        assertThat(contract.get().getVehicle().getId()).isEqualTo(contractCreateDto.getVehicleId());
        assertThat(contract.get().getCustomer().getId()).isEqualTo(contractCreateDto.getCustomerId());
    }

    @Test
    void testCreateContractReturnsPreconditionFailed() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        dataCreator.createContract(vehicle, customer);

        ContractCreateDto contractCreateDto = new ContractCreateDto()
            .setContractNumber(new BigDecimal(faker.number().numberBetween(100000, 999999)))
            .setMonthlyRate(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1500)))
            .setCustomerId(customer.getId())
            .setVehicleId(vehicle.getId());

        performAndPost(mockMvc, status().isPreconditionFailed(), contractCreateDto, "/contracts");

        List<Contract> contract = contractRepository.findAll();

        assertThat(contract).hasSize(1);
    }

    @Test
    void testCreateContractReturnsNotFoundForVehicle() throws Exception {
        Customer customer = dataCreator.createCustomer();

        ContractCreateDto contractCreateDto = new ContractCreateDto()
            .setContractNumber(new BigDecimal(faker.number().numberBetween(100000, 999999)))
            .setMonthlyRate(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1500)))
            .setCustomerId(customer.getId())
            .setVehicleId(UUID.randomUUID());

        performAndPost(mockMvc, status().isNotFound(), contractCreateDto, "/contracts");

        List<Contract> contract = contractRepository.findAll();

        assertThat(contract).isEmpty();
    }

    @Test
    void testCreateContractReturnsNotFoundForCustomer() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();

        ContractCreateDto contractCreateDto = new ContractCreateDto()
            .setContractNumber(new BigDecimal(faker.number().numberBetween(100000, 999999)))
            .setMonthlyRate(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1500)))
            .setCustomerId(UUID.randomUUID())
            .setVehicleId(vehicle.getId());

        performAndPost(mockMvc, status().isNotFound(), contractCreateDto, "/contracts");

        List<Contract> contract = contractRepository.findAll();

        assertThat(contract).isEmpty();
    }
}