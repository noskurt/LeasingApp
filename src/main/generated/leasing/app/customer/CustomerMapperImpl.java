package leasing.app.customer;

import javax.annotation.processing.Generated;
import leasing.app.customer.dto.request.CustomerCreateDto;
import leasing.app.customer.dto.request.CustomerUpdateDto;
import leasing.app.customer.dto.response.CustomerGetDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-25T16:48:31+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toCustomer(CustomerCreateDto customerCreateDto) {
        if ( customerCreateDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setFirstName( customerCreateDto.getFirstName() );
        customer.setLastName( customerCreateDto.getLastName() );
        customer.setBirthdate( customerCreateDto.getBirthdate() );

        return customer;
    }

    @Override
    public Customer toCustomer(Customer customer, CustomerUpdateDto customerUpdateDto) {
        if ( customerUpdateDto == null ) {
            return customer;
        }

        customer.setFirstName( customerUpdateDto.getFirstName() );
        customer.setLastName( customerUpdateDto.getLastName() );
        customer.setBirthdate( customerUpdateDto.getBirthdate() );

        return customer;
    }

    @Override
    public CustomerGetDto toCustomerGetDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerGetDto customerGetDto = new CustomerGetDto();

        customerGetDto.setId( customer.getId() );
        customerGetDto.setFirstName( customer.getFirstName() );
        customerGetDto.setLastName( customer.getLastName() );
        customerGetDto.setBirthdate( customer.getBirthdate() );

        return customerGetDto;
    }
}
