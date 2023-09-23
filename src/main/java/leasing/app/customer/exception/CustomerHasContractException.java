package leasing.app.customer.exception;

import leasing.app.exception.ConditionNotMetException;

import java.util.UUID;

public class CustomerHasContractException extends ConditionNotMetException {
    public CustomerHasContractException(UUID customerId) {
        super("Customer has already connected to a Contract, Customer Id: " + customerId);
    }
}
