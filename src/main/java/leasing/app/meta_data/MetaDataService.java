package leasing.app.meta_data;

import leasing.app.meta_data.dto.BrandGetDto;
import leasing.app.meta_data.dto.ModelGetDto;

import java.util.List;
import java.util.UUID;

public interface MetaDataService {
    List<BrandGetDto> getAllBrands();
    List<ModelGetDto> getModelByBrandId(UUID brandId);
}
