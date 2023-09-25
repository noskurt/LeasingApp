package leasing.app.customer;

import leasing.app.BaseTest;
import leasing.app.customer.dto.request.CustomerUpdateDto;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UpdateCustomerTest extends BaseTest {

    private static final String URL = "/customers/{customerId}";

    @Test
    void testUpdateCustomerReturnsSuccess() throws Exception {
        Customer customer = dataCreator.createCustomer();

        CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto()
            .setFirstName(faker.name().firstName())
            .setLastName(faker.name().lastName())
            .setBirthdate(faker.date().birthday().toLocalDateTime().toLocalDate());

        performAndPut(mockMvc, status().isOk(), customerUpdateDto, URL, customer.getId());

        Optional<Customer> customerRecord = customerRepository.findAll().stream().findFirst();

        assertThat(customerRecord).isPresent();
        assertThat(customerRecord.get().getFirstName()).isEqualTo(customerUpdateDto.getFirstName());
        assertThat(customerRecord.get().getLastName()).isEqualTo(customerUpdateDto.getLastName());
        assertThat(customerRecord.get().getBirthdate()).isEqualTo(customerUpdateDto.getBirthdate());
    }

    @Test
    void testUpdateCustomerReturnsNotFound() throws Exception {
        Customer customer = dataCreator.createCustomer();

        CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto()
            .setFirstName(faker.name().firstName())
            .setLastName(faker.name().lastName())
            .setBirthdate(faker.date().birthday().toLocalDateTime().toLocalDate());

        performAndPut(mockMvc, status().isNotFound(), customerUpdateDto, URL, UUID.randomUUID());

        Optional<Customer> customerRecord = customerRepository.findAll().stream().findFirst();

        assertThat(customerRecord).isPresent();
        assertThat(customerRecord.get().getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(customerRecord.get().getLastName()).isEqualTo(customer.getLastName());
        assertThat(customerRecord.get().getBirthdate()).isEqualTo(customer.getBirthdate());
    }
}