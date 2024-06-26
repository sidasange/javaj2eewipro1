package com.abc.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.customerservice.entity.Customer;
import com.abc.customerservice.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/all")
	public List<Customer> fetchAllCustomers() {
		List<Customer> customers =customerService.getAllcustomer();
		return  customers;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable("id") int customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);

	}

	@PostMapping("/save")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customer, HttpStatus.CREATED);
		return responseEntity;
	}

	@PutMapping("/update")
	public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer) {
		customerService.updateCustomer(customer);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int customerId) {
		customerService.deleteCustomer(customerId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;

	}
	

}
