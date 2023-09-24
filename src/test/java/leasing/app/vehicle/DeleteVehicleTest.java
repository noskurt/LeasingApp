package leasing.app.vehicle;

import leasing.app.BaseTest;
import leasing.app.customer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeleteVehicleTest extends BaseTest {

    @Test
    void testDeleteVehicleEndpointReturnsSuccess() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();

        mockMvc.perform(delete("/vehicles/{vehicleId}", vehicle.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        Optional<Vehicle> vehicleRecord = vehicleRepository.findById(vehicle.getId());
        assertThat(vehicleRecord).isNotPresent();
    }

    @Test
    void testDeleteVehicleEndpointReturnsNotFound() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();

        mockMvc.perform(delete("/vehicles/{vehicleId}", UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        Optional<Vehicle> vehicleRecord = vehicleRepository.findById(vehicle.getId());
        assertThat(vehicleRecord).isPresent();
    }

    @Test
    void testDeleteVehicleEndpointReturnsPreconditionFailed() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();
        Customer customer = dataCreator.createCustomer();
        dataCreator.createContract(vehicle, customer);

        mockMvc.perform(delete("/vehicles/{vehicleId}", vehicle.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isPreconditionFailed());

        Optional<Vehicle> vehicleRecord = vehicleRepository.findById(vehicle.getId());
        assertThat(vehicleRecord).isPresent();
    }
}