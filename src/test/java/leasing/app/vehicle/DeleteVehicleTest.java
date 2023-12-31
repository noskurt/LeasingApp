package leasing.app.vehicle;

import leasing.app.BaseTest;
import leasing.app.customer.Customer;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeleteVehicleTest extends BaseTest {

    private static final String URL = "/vehicles/{vehicleId}";

    @Test
    void testDeleteVehicleReturnsSuccess() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();

        performAndDelete(mockMvc, status().isOk(), URL, vehicle.getId());

        Optional<Vehicle> vehicleRecord = vehicleRepository.findById(vehicle.getId());
        assertThat(vehicleRecord).isNotPresent();
    }

    @Test
    void testDeleteVehicleReturnsNotFound() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();

        performAndDelete(mockMvc, status().isNotFound(), URL, UUID.randomUUID());

        Optional<Vehicle> vehicleRecord = vehicleRepository.findById(vehicle.getId());
        assertThat(vehicleRecord).isPresent();
    }

    @Test
    void testDeleteVehicleReturnsPreconditionFailed() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        dataCreator.createContract(vehicle, customer);

        performAndDelete(mockMvc, status().isPreconditionFailed(), URL, vehicle.getId());

        Optional<Vehicle> vehicleRecord = vehicleRepository.findById(vehicle.getId());
        assertThat(vehicleRecord).isPresent();
    }
}