package leasing.app.customer;

import leasing.app.customer.dto.request.CustomerCreateDto;
import leasing.app.customer.dto.request.CustomerUpdateDto;
import leasing.app.customer.dto.response.CustomerGetDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    void createCustomer(CustomerCreateDto customerCreateDto);
    CustomerGetDto getCustomer(UUID customerId);
    List<CustomerGetDto> getAllCustomers();
    void deleteCustomer(UUID customerId);
    void updateCustomer(UUID customerIdm, CustomerUpdateDto customerUpdateDto);
}
