package io.enosix.salesdoc.services;

import io.enosix.salesdoc.resources.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers(int pageNumber, int pageSize);
}
