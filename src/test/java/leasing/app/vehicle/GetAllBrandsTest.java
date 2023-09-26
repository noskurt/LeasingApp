package leasing.app.vehicle;

import leasing.app.BaseTest;
import leasing.app.meta_data.Brand;
import leasing.app.meta_data.dto.response.BrandGetDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetAllBrandsTest extends BaseTest {

    private static final String URL = "/vehicles/brands";

    @Test
    void testGetAllBrandsReturnsSuccess() throws Exception {
        List<BrandGetDto> result = performAndGetResultList(mockMvc, BrandGetDto.class, status().isOk(), URL);
        List<Brand> brands = brandRepository.findAll();

        assertThat(result).hasSameSizeAs(brands);

        brands.forEach(brand -> {
            Optional<BrandGetDto> brandItem = result.stream().filter(e -> e.getId().equals(brand.getId())).findFirst();
            assertThat(brandItem).isPresent();
            assertThat(brand.getName()).isEqualTo(brandItem.get().getName());
        });
    }
}