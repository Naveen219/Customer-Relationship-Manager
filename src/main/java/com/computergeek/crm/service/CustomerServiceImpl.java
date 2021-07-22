package com.computergeek.crm.service;

import com.computergeek.crm.entity.Customer;
import com.computergeek.crm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepo;
    @Override
    public void addCustomer(Customer customer) {
        this.customerRepo.save(customer);

    }
    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        Customer customer = null;
        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
            customerRepo.delete(customer);
        }
        else {
            throw new RuntimeException("Customer not found for id " + id);
        }
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        Customer customer = null;
        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
        }
        else {
            throw new RuntimeException("Customer not found for id " + id);
        }
        return customer;
    }
    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepo.findAll();
    }
}
