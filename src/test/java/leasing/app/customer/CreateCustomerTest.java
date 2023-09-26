package leasing.app.customer;

import leasing.app.BaseTest;
import leasing.app.customer.dto.request.CustomerCreateDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateCustomerTest extends BaseTest {

    private static final String URL = "/customers";

    @Test
    void testCreateCustomerReturnsSuccess() throws Exception {
        CustomerCreateDto customerCreateDto = new CustomerCreateDto()
            .setFirstName(faker.name().firstName())
            .setLastName(faker.name().lastName())
            .setBirthdate(faker.date().birthday().toLocalDateTime().toLocalDate());

        performAndPost(mockMvc, status().isOk(), customerCreateDto, URL);

        Optional<Customer> customer = customerRepository.findAll().stream().findFirst();

        assertThat(customer).isPresent();
        assertThat(customer.get().getFirstName()).isEqualTo(customerCreateDto.getFirstName());
        assertThat(customer.get().getLastName()).isEqualTo(customerCreateDto.getLastName());
        assertThat(customer.get().getBirthdate()).isEqualTo(customerCreateDto.getBirthdate());
    }

    @Test
    void testCreateCustomerReturnsBadRequest() throws Exception {
        CustomerCreateDto customerCreateDto = new CustomerCreateDto()
            .setFirstName(null)
            .setLastName(faker.name().lastName())
            .setBirthdate(faker.date().birthday().toLocalDateTime().toLocalDate());

        performAndPost(mockMvc, status().isBadRequest(), customerCreateDto, URL);

        List<Customer> customers = customerRepository.findAll();

        assertThat(customers).isEmpty();
    }
}