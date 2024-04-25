package com.abc.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.customerservice.entity.Customer;
import com.abc.customerservice.exception.ResourceNotFoundException;
import com.abc.customerservice.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer user) {
	 customerRepository.save(user);
		return user;
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Optional<Customer> optionalcustomer = customerRepository.findById(customerId);
		if(optionalcustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not found with customer id "+customerId);
			
		}
		return optionalcustomer.get();
	}

	@Override
	public List<Customer> getAllcustomer() {
		List<Customer> users = customerRepository.findAll();
		return users;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
       Optional<Customer> optionalcustomer = customerRepository.findById(customer.getCustomerId());
       if(optionalcustomer.isEmpty()) {
    	   throw new ResourceNotFoundException("Customer not found with id "+customer.getCustomerId());
       }
       customerRepository.save(customer);
		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {
		Optional<Customer> optionalcustomer = customerRepository.findById(customerId);
		if(optionalcustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not found with customer id "+customerId);
			
		}
		Customer customer =optionalcustomer.get();
		customerRepository.delete(customer);
		
	}

}
