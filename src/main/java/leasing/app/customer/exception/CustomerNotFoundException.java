package leasing.app.customer.exception;

import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public class CustomerNotFoundException extends EntityNotFoundException {
    public CustomerNotFoundException(UUID customerId) {
        super("Customer Not Found by Id: " + customerId);
    }
}
