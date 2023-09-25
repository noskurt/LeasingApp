package leasing.app.contract;

import leasing.app.BaseTest;
import leasing.app.contract.dto.response.ContractGetDto;
import leasing.app.customer.Customer;
import leasing.app.vehicle.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetAllContractTest extends BaseTest {

    private static final String URL = "/contracts";

    @Test
    void testGetAllContractReturnsSuccess() throws Exception {
        Vehicle vehicleFirst = dataCreator.createVehicle();
        Customer customerFirst = dataCreator.createCustomer();
        Contract contractFirst = dataCreator.createContract(vehicleFirst, customerFirst);
        
        Vehicle vehicleSecond = dataCreator.createVehicle();
        Customer customerSecond = dataCreator.createCustomer();
        Contract contractSecond = dataCreator.createContract(vehicleSecond, customerSecond);

        List<ContractGetDto> result = performAndGetResultList(mockMvc, ContractGetDto.class, status().isOk(), URL);

        assertThat(result).hasSize(2);

        Optional<ContractGetDto> resultFirst = result.stream().filter(e -> e.getId().equals(contractFirst.getId())).findFirst();
        assertThat(resultFirst).isPresent();

        assertThat(contractFirst.getId()).isEqualTo(resultFirst.get().getId());
        assertThat(contractFirst.getContractNumber()).isEqualTo(resultFirst.get().getContractNumber());
        assertThat(contractFirst.getMonthlyRate()).isEqualTo(resultFirst.get().getMonthlyRate());
        assertThat(contractFirst.getVehicle().getId()).isEqualTo(resultFirst.get().getVehicle().getId());
        assertThat(contractFirst.getCustomer().getId()).isEqualTo(resultFirst.get().getCustomer().getId());

        Optional<ContractGetDto> resultSecond = result.stream().filter(e -> e.getId().equals(contractSecond.getId())).findFirst();
        assertThat(resultSecond).isPresent();

        assertThat(contractSecond.getId()).isEqualTo(resultSecond.get().getId());
        assertThat(contractSecond.getContractNumber()).isEqualTo(resultSecond.get().getContractNumber());
        assertThat(contractSecond.getMonthlyRate()).isEqualTo(resultSecond.get().getMonthlyRate());
        assertThat(contractSecond.getVehicle().getId()).isEqualTo(resultSecond.get().getVehicle().getId());
        assertThat(contractSecond.getCustomer().getId()).isEqualTo(resultSecond.get().getCustomer().getId());
    }

}