package leasing.app.contract;

import leasing.app.BaseTest;
import leasing.app.contract.dto.response.ContractGetDto;
import leasing.app.customer.Customer;
import leasing.app.vehicle.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetContractTest extends BaseTest {

    private static final String URL = "/contracts/{contractId}";

    @Test
    void testGetContractAndReturnsSuccess() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        Contract contract = dataCreator.createContract(vehicle, customer);

        ContractGetDto result = performAndGetResult(mockMvc, ContractGetDto.class, status().isOk(), URL, contract.getId());

        assertThat(contract.getId()).isEqualTo(result.getId());
        assertThat(contract.getContractNumber()).isEqualTo(result.getContractNumber());
        assertThat(contract.getMonthlyRate()).isEqualTo(result.getMonthlyRate());
        assertThat(contract.getVehicle().getId()).isEqualTo(result.getVehicle().getId());
        assertThat(contract.getCustomer().getId()).isEqualTo(result.getCustomer().getId());
    }

    @Test
    void testGetContractReturnsNotFound() throws Exception {
        performAndGetResult(mockMvc, ContractGetDto.class, status().isNotFound(), URL, UUID.randomUUID());
    }
}