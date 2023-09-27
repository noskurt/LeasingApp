package leasing.app.vehicle;

import leasing.app.BaseTest;
import leasing.app.meta_data.Brand;
import leasing.app.meta_data.Model;
import leasing.app.meta_data.dto.ModelGetDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetModelByBrandIdTest extends BaseTest {

    private static final String URL = "/vehicles/brands/{brandId}/models";

    @Test
    void testGetModelByBrandIdReturnsSuccess() throws Exception {
        Optional<Brand> randomBrand = brandRepository.findAll().stream().findFirst();
        assertThat(randomBrand).isPresent();

        List<ModelGetDto> result = performAndGetResultList(mockMvc, ModelGetDto.class, status().isOk(), URL, randomBrand.get().getId());
        List<Model> models = modelRepository.findByBrandId(randomBrand.get().getId());

        assertThat(result).hasSameSizeAs(models);

        models.forEach(model -> {
            Optional<ModelGetDto> modelItem = result.stream().filter(e -> e.getId().equals(model.getId())).findFirst();
            assertThat(modelItem).isPresent();
            assertThat(model.getName()).isEqualTo(modelItem.get().getName());
        });
    }

    @Test
    void testGetModelByBrandIdReturnsEmptyList() throws Exception {
        Optional<Brand> randomBrand = brandRepository.findAll().stream().findFirst();
        assertThat(randomBrand).isPresent();

        List<ModelGetDto> result = performAndGetResultList(mockMvc, ModelGetDto.class, status().isOk(), URL, UUID.randomUUID());

        assertThat(result).isEmpty();
    }
}