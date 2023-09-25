package leasing.app.vehicle;

import leasing.app.BaseTest;
import leasing.app.vehicle.dto.request.VehicleUpdateDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UpdateVehicleTest extends BaseTest {

    private static final String URL = "/vehicles/{vehicleId}";

    @Test
    void testUpdateVehicleReturnsSuccess() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();

        String brand = faker.vehicle().make();
        String model = faker.vehicle().model(brand);

        VehicleUpdateDto vehicleUpdateDto = new VehicleUpdateDto()
            .setBrand(brand)
            .setModel(model)
            .setYear(faker.number().numberBetween(1990, 2023))
            .setPrice(new BigDecimal(faker.number().numberBetween(10000, 150000)))
            .setVin(faker.random().nextBoolean() ? faker.vehicle().vin() : null);

        performAndPut(mockMvc, status().isOk(), vehicleUpdateDto, URL, vehicle.getId());

        Optional<Vehicle> vehicleRecord = vehicleRepository.findAll().stream().findFirst();

        assertThat(vehicleRecord).isPresent();
        assertThat(vehicleRecord.get().getBrand()).isEqualTo(vehicleUpdateDto.getBrand());
        assertThat(vehicleRecord.get().getModel()).isEqualTo(vehicleUpdateDto.getModel());
        assertThat(vehicleRecord.get().getYear()).isEqualTo(vehicleUpdateDto.getYear());
        assertThat(vehicleRecord.get().getVin()).isEqualTo(vehicleUpdateDto.getVin());
        assertThat(vehicleRecord.get().getPrice()).isEqualTo(vehicleUpdateDto.getPrice());
    }

    @Test
    void testUpdateVehicleReturnsNotFound() throws Exception {
        Vehicle vehicle = dataCreator.createVehicle();

        String brand = faker.vehicle().make();
        String model = faker.vehicle().model(brand);

        VehicleUpdateDto vehicleUpdateDto = new VehicleUpdateDto()
            .setBrand(brand)
            .setModel(model)
            .setYear(faker.number().numberBetween(1990, 2023))
            .setPrice(new BigDecimal(faker.number().numberBetween(10000, 150000)))
            .setVin(faker.random().nextBoolean() ? faker.vehicle().vin() : null);

        performAndPut(mockMvc, status().isNotFound(), vehicleUpdateDto, URL, UUID.randomUUID());

        Optional<Vehicle> vehicleRecord = vehicleRepository.findAll().stream().findFirst();

        assertThat(vehicleRecord).isPresent();
        assertThat(vehicleRecord.get().getBrand()).isEqualTo(vehicle.getBrand());
        assertThat(vehicleRecord.get().getModel()).isEqualTo(vehicle.getModel());
        assertThat(vehicleRecord.get().getYear()).isEqualTo(vehicle.getYear());
        assertThat(vehicleRecord.get().getVin()).isEqualTo(vehicle.getVin());
        assertThat(vehicleRecord.get().getPrice()).isEqualTo(vehicle.getPrice());
    }
}