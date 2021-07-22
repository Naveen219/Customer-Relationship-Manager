package com.computergeek.crm.service;

import com.computergeek.crm.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void addCustomer(Customer customer);
    void deleteCustomer(Long id);
    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

}
