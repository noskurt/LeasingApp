package leasing.app.meta_data;

import leasing.app.meta_data.dto.BrandGetDto;
import leasing.app.meta_data.dto.ModelGetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MetaDataServiceImpl implements MetaDataService {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final MetaDataMapper metaDataMapper;

    @Override
    public List<BrandGetDto> getAllBrands() {
        return brandRepository.findAll().stream().map(metaDataMapper::toBrandGetDto).toList();
    }

    @Override
    public List<ModelGetDto> getModelByBrandId(UUID brandId) {
        return modelRepository.findByBrandId(brandId).stream().map(metaDataMapper::toModelGetDto).toList();
    }
}
