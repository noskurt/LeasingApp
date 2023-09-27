package leasing.app.meta_data;

import leasing.app.meta_data.dto.BrandGetDto;
import leasing.app.meta_data.dto.ModelGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MetaDataMapper {
    BrandGetDto toBrandGetDto(Brand brand);
    ModelGetDto toModelGetDto(Model model);
}
