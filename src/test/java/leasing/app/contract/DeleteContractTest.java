package leasing.app.contract;

import leasing.app.BaseTest;
import leasing.app.customer.Customer;
import leasing.app.vehicle.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeleteContractTest extends BaseTest {

    @Test
    void testDeleteContractReturnsSuccess() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        Contract contract = dataCreator.createContract(vehicle, customer);

        performAndDelete(mockMvc, status().isOk(), "/contracts/{contractId}", contract.getId());

        Optional<Contract> contractRecord = contractRepository.findById(contract.getId());
        assertThat(contractRecord).isNotPresent();
    }

    @Test
    void testDeleteContractReturnsNotFound() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        Contract contract = dataCreator.createContract(vehicle, customer);

        performAndDelete(mockMvc, status().isNotFound(), "/contracts/{contractId}", UUID.randomUUID());

        Optional<Contract> contractRecord = contractRepository.findById(contract.getId());
        assertThat(contractRecord).isPresent();
    }
}