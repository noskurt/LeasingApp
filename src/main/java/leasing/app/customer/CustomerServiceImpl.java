package leasing.app.customer;

import leasing.app.contract.ContractRepository;
import leasing.app.customer.dto.request.CustomerCreateDto;
import leasing.app.customer.dto.request.CustomerUpdateDto;
import leasing.app.customer.dto.response.CustomerGetDto;
import leasing.app.customer.exception.CustomerHasContractException;
import leasing.app.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ContractRepository contractRepository;
    private final CustomerMapper customerMapper;

    @Override
    public void createCustomer(CustomerCreateDto customerCreateDto) {
        Customer customer = customerMapper.toCustomer(customerCreateDto);
        customerRepository.save(customer);
    }

    @Override
    public CustomerGetDto getCustomer(UUID customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        return customerMapper.toCustomerGetDto(customer);
    }

    @Override
    public List<CustomerGetDto> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return allCustomers.stream().map(customerMapper::toCustomerGetDto).toList();
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        boolean isContractExists = contractRepository.existsByCustomerId(customerId);
        if (isContractExists) throw new CustomerHasContractException(customerId);
        customerRepository.delete(customer);
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerUpdateDto customerUpdateDto) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        Customer updatedCustomer = customerMapper.toCustomer(customer, customerUpdateDto);
        customerRepository.save(updatedCustomer);
    }
}
