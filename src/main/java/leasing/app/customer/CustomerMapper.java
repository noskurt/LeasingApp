package leasing.app.customer;

import leasing.app.customer.dto.request.CustomerCreateDto;
import leasing.app.customer.dto.request.CustomerUpdateDto;
import leasing.app.customer.dto.response.CustomerGetDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerCreateDto customerCreateDto) {
        return new Customer()
            .setFirstName(customerCreateDto.getFirstName())
            .setLastName(customerCreateDto.getLastName())
            .setBirthdate(customerCreateDto.getBirthdate());
    }

    public Customer toCustomer(Customer customer, CustomerUpdateDto customerUpdateDto) {
        return customer
            .setFirstName(customerUpdateDto.getFirstName())
            .setLastName(customerUpdateDto.getLastName())
            .setBirthdate(customerUpdateDto.getBirthdate());
    }

    public CustomerGetDto toCustomerGetDto(Customer customer) {
        return new CustomerGetDto(
            customer.getId(),
            customer.getFirstName(),
            customer.getLastName(),
            customer.getBirthdate()
        );
    }
}
