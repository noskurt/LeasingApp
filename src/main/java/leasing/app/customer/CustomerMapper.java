package leasing.app.customer;

import leasing.app.customer.dto.request.CustomerCreateDto;
import leasing.app.customer.dto.request.CustomerUpdateDto;
import leasing.app.customer.dto.response.CustomerGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    Customer toCustomer(CustomerCreateDto customerCreateDto);

    Customer toCustomer(@MappingTarget Customer customer, CustomerUpdateDto customerUpdateDto);

    CustomerGetDto toCustomerGetDto(Customer customer);
}
