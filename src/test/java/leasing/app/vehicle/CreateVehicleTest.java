package leasing.app.vehicle;

import leasing.app.BaseTest;
import leasing.app.vehicle.dto.request.VehicleCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreateVehicleTest extends BaseTest {

    @Test
    void testCreateVehicleEndpointReturnsSuccess() throws Exception {
        String brand = faker.vehicle().make();
        String model = faker.vehicle().model(brand);

        VehicleCreateDto vehicleCreateDto = new VehicleCreateDto()
            .setBrand(brand)
            .setModel(model)
            .setYear(faker.number().numberBetween(1990, 2023))
            .setPrice(new BigDecimal(faker.number().numberBetween(10000, 150000)))
            .setVin(faker.random().nextBoolean() ? faker.vehicle().vin() : null);

        mockMvc.perform(post("/vehicles")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleCreateDto)))
            .andExpect(status().isOk());

        Optional<Vehicle> vehicle = vehicleRepository.findAll().stream().findFirst();

        assertThat(vehicle).isPresent();
        assertThat(vehicle.get().getBrand()).isEqualTo(vehicleCreateDto.getBrand());
        assertThat(vehicle.get().getModel()).isEqualTo(vehicleCreateDto.getModel());
        assertThat(vehicle.get().getYear()).isEqualTo(vehicleCreateDto.getYear());
        assertThat(vehicle.get().getVin()).isEqualTo(vehicleCreateDto.getVin());
        assertThat(vehicle.get().getPrice()).isEqualTo(vehicleCreateDto.getPrice());
    }
}