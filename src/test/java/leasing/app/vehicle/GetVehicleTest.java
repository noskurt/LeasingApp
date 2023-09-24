package leasing.app.vehicle;

import leasing.app.BaseTest;
import leasing.app.vehicle.dto.response.VehicleGetDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetVehicleTest extends BaseTest {

    @Test
    void testGetVehicleEndpointReturnsSuccess() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();

        MvcResult mvcResult = mockMvc.perform(get("/vehicles/{vehicleId}", vehicle.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
        VehicleGetDto result = andReturnResult(mvcResult, VehicleGetDto.class);

        assertThat(vehicle.getId()).isEqualTo(result.getId());
        assertThat(vehicle.getBrand()).isEqualTo(result.getBrand());
        assertThat(vehicle.getModel()).isEqualTo(result.getModel());
        assertThat(vehicle.getYear()).isEqualTo(result.getYear());
        assertThat(vehicle.getVin()).isEqualTo(result.getVin());
        assertThat(vehicle.getPrice()).isEqualTo(result.getPrice());
    }

    @Test
    void testGetVehicleEndpointReturnsNotFound() throws Exception {
        mockMvc.perform(get("/vehicles/{vehicleId}", UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
}