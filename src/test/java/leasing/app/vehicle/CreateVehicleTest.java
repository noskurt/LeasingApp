package leasing.app.vehicle;

import leasing.app.BaseTest;
import leasing.app.vehicle.dto.request.VehicleCreateDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateVehicleTest extends BaseTest {

    private static final String URL = "/vehicles";

    @Test
    void testCreateVehicleReturnsSuccess() throws Exception {
        String brand = faker.vehicle().make();
        String model = faker.vehicle().model(brand);

        VehicleCreateDto vehicleCreateDto = new VehicleCreateDto()
            .setBrand(brand)
            .setModel(model)
            .setYear(faker.number().numberBetween(1990, 2023))
            .setPrice(new BigDecimal(faker.number().numberBetween(10000, 150000)))
            .setVin(faker.random().nextBoolean() ? faker.vehicle().vin() : null);

        performAndPost(mockMvc, status().isOk(), vehicleCreateDto, URL);

        Optional<Vehicle> vehicle = vehicleRepository.findAll().stream().findFirst();

        assertThat(vehicle).isPresent();
        assertThat(vehicle.get().getBrand()).isEqualTo(vehicleCreateDto.getBrand());
        assertThat(vehicle.get().getModel()).isEqualTo(vehicleCreateDto.getModel());
        assertThat(vehicle.get().getYear()).isEqualTo(vehicleCreateDto.getYear());
        assertThat(vehicle.get().getVin()).isEqualTo(vehicleCreateDto.getVin());
        assertThat(vehicle.get().getPrice()).isEqualTo(vehicleCreateDto.getPrice());
    }

    @Test
    void testCreateVehicleReturnsBadRequest() throws Exception {
        VehicleCreateDto vehicleCreateDto = new VehicleCreateDto()
            .setBrand(null)
            .setModel(null)
            .setYear(faker.number().numberBetween(1990, 2023))
            .setPrice(new BigDecimal(faker.number().numberBetween(10000, 150000)))
            .setVin(faker.random().nextBoolean() ? faker.vehicle().vin() : null);

        performAndPost(mockMvc, status().isBadRequest(), vehicleCreateDto, URL);

        List<Vehicle> vehicles = vehicleRepository.findAll();

        assertThat(vehicles).isEmpty();
    }
}