package com.abc.customerservice.service;

import java.util.List;

import com.abc.customerservice.entity.Customer;

public interface CustomerService {
	
	Customer addCustomer(Customer user);
	
	Customer getCustomerById(int customerId);
	
	List<Customer> getAllcustomer();
	
	Customer updateCustomer(Customer user);
	
	void deleteCustomer(int customerId);


	

}
