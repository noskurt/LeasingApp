package leasing.app.customer;

import leasing.app.BaseTest;
import leasing.app.customer.dto.response.CustomerGetDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetAllCustomerTest extends BaseTest {

    private static final String URL = "/customers";

    @Test
    void testGetAllCustomerReturnsSuccess() throws Exception {
        Customer customerFirst = dataCreator.createCustomer();
        Customer customerSecond = dataCreator.createCustomer();

        List<CustomerGetDto> result = performAndGetResultList(mockMvc, CustomerGetDto.class, status().isOk(), URL);

        assertThat(result).hasSize(2);

        Optional<CustomerGetDto> resultFirst = result.stream().filter(e -> e.getId().equals(customerFirst.getId())).findFirst();
        assertThat(resultFirst).isPresent();

        assertThat(customerFirst.getId()).isEqualTo(resultFirst.get().getId());
        assertThat(customerFirst.getFirstName()).isEqualTo(resultFirst.get().getFirstName());
        assertThat(customerFirst.getLastName()).isEqualTo(resultFirst.get().getLastName());
        assertThat(customerFirst.getBirthdate()).isEqualTo(resultFirst.get().getBirthdate());

        Optional<CustomerGetDto> resultSecond = result.stream().filter(e -> e.getId().equals(customerSecond.getId())).findFirst();
        assertThat(resultSecond).isPresent();

        assertThat(customerSecond.getId()).isEqualTo(resultSecond.get().getId());
        assertThat(customerSecond.getFirstName()).isEqualTo(resultSecond.get().getFirstName());
        assertThat(customerSecond.getLastName()).isEqualTo(resultSecond.get().getLastName());
        assertThat(customerSecond.getBirthdate()).isEqualTo(resultSecond.get().getBirthdate());
    }

}