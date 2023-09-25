package leasing.app.vehicle;

import leasing.app.BaseTest;
import leasing.app.vehicle.dto.response.VehicleGetDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetVehicleTest extends BaseTest {

    private static final String URL = "/vehicles/{vehicleId}";

    @Test
    void testGetVehicleAndReturnsSuccess() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();

        VehicleGetDto result = performAndGetResult(mockMvc, VehicleGetDto.class, status().isOk(), URL, vehicle.getId());

        assertThat(vehicle.getId()).isEqualTo(result.getId());
        assertThat(vehicle.getBrand()).isEqualTo(result.getBrand());
        assertThat(vehicle.getModel()).isEqualTo(result.getModel());
        assertThat(vehicle.getYear()).isEqualTo(result.getYear());
        assertThat(vehicle.getVin()).isEqualTo(result.getVin());
        assertThat(vehicle.getPrice()).isEqualTo(result.getPrice());
    }

    @Test
    void testGetVehicleReturnsNotFound() throws Exception {
        performAndGetResult(mockMvc, VehicleGetDto.class, status().isNotFound(), URL, UUID.randomUUID());
    }
}