package leasing.app.customer;

import leasing.app.BaseTest;
import leasing.app.customer.dto.response.CustomerGetDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetCustomerTest extends BaseTest {

    @Test
    void testGetCustomerAndReturnsSuccess() throws Exception {
        Customer customer = dataCreator.createCustomer();

        CustomerGetDto result = performAndGetResult(mockMvc, CustomerGetDto.class, status().isOk(), "/customers/{customerId}", customer.getId());

        assertThat(customer.getId()).isEqualTo(result.getId());
        assertThat(customer.getFirstName()).isEqualTo(result.getFirstName());
        assertThat(customer.getLastName()).isEqualTo(result.getLastName());
        assertThat(customer.getBirthdate()).isEqualTo(result.getBirthdate());
    }

    @Test
    void testGetCustomerReturnsNotFound() throws Exception {
        performAndGetResult(mockMvc, CustomerGetDto.class, status().isNotFound(), "/customers/{customerId}", UUID.randomUUID());
    }
}