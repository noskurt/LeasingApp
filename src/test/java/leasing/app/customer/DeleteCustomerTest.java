package leasing.app.customer;

import leasing.app.BaseTest;
import leasing.app.vehicle.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeleteCustomerTest extends BaseTest {

    @Test
    void testDeleteCustomerReturnsSuccess() throws Exception {
        Customer customer = dataCreator.createCustomer();

        performAndDelete(mockMvc, status().isOk(), "/customers/{customerId}", customer.getId());

        Optional<Customer> customerRecord = customerRepository.findById(customer.getId());
        assertThat(customerRecord).isNotPresent();
    }

    @Test
    void testDeleteCustomerReturnsNotFound() throws Exception {
        Customer customer = dataCreator.createCustomer();

        performAndDelete(mockMvc, status().isNotFound(), "/customers/{customerId}", UUID.randomUUID());

        Optional<Customer> customerRecord = customerRepository.findById(customer.getId());
        assertThat(customerRecord).isPresent();
    }

    @Test
    void testDeleteCustomerReturnsPreconditionFailed() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        dataCreator.createContract(vehicle, customer);

        performAndDelete(mockMvc, status().isPreconditionFailed(), "/customers/{customerId}", customer.getId());

        Optional<Customer> customerRecord = customerRepository.findById(customer.getId());
        assertThat(customerRecord).isPresent();
    }
}