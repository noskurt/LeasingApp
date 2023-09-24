package leasing.app.customer;

import leasing.app.customer.dto.request.CustomerCreateDto;
import leasing.app.customer.dto.request.CustomerUpdateDto;
import leasing.app.customer.dto.response.CustomerGetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    
    private final CustomerService customerService;

    @PostMapping
    public void createCustomer(@RequestBody CustomerCreateDto customerCreateDto) {
        customerService.createCustomer(customerCreateDto);
    }

    @GetMapping("/{customerId}")
    public CustomerGetDto getCustomer(@PathVariable UUID customerId) {
        return customerService.getCustomer(customerId);
    }

    @GetMapping
    public List<CustomerGetDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable UUID customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(@PathVariable UUID customerId, @RequestBody CustomerUpdateDto customerUpdateDto){
        customerService.updateCustomer(customerId, customerUpdateDto);
    }
    
}
