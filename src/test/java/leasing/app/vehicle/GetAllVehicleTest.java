package leasing.app.vehicle;

import leasing.app.BaseTest;
import leasing.app.vehicle.dto.response.VehicleGetDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetAllVehicleTest extends BaseTest {

    private static final String URL = "/vehicles";

    @Test
    void testGetAllVehicleReturnsSuccess() throws Exception {
        Vehicle vehicleFirst = dataCreator.createVehicle();
        Vehicle vehicleSecond = dataCreator.createVehicle();

        List<VehicleGetDto> result = performAndGetResultList(mockMvc, VehicleGetDto.class, status().isOk(), URL);

        assertThat(result).hasSize(2);

        Optional<VehicleGetDto> resultFirst = result.stream().filter(e -> e.getId().equals(vehicleFirst.getId())).findFirst();
        assertThat(resultFirst).isPresent();

        assertThat(vehicleFirst.getId()).isEqualTo(resultFirst.get().getId());
        assertThat(vehicleFirst.getBrand()).isEqualTo(resultFirst.get().getBrand());
        assertThat(vehicleFirst.getModel()).isEqualTo(resultFirst.get().getModel());
        assertThat(vehicleFirst.getYear()).isEqualTo(resultFirst.get().getYear());
        assertThat(vehicleFirst.getVin()).isEqualTo(resultFirst.get().getVin());
        assertThat(vehicleFirst.getPrice()).isEqualTo(resultFirst.get().getPrice());

        Optional<VehicleGetDto> resultSecond = result.stream().filter(e -> e.getId().equals(vehicleSecond.getId())).findFirst();
        assertThat(resultSecond).isPresent();

        assertThat(vehicleSecond.getId()).isEqualTo(resultSecond.get().getId());
        assertThat(vehicleSecond.getBrand()).isEqualTo(resultSecond.get().getBrand());
        assertThat(vehicleSecond.getModel()).isEqualTo(resultSecond.get().getModel());
        assertThat(vehicleSecond.getYear()).isEqualTo(resultSecond.get().getYear());
        assertThat(vehicleSecond.getVin()).isEqualTo(resultSecond.get().getVin());
        assertThat(vehicleSecond.getPrice()).isEqualTo(resultSecond.get().getPrice());
    }

}