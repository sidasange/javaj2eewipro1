package com.hotelbooking.paymentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbooking.paymentservice.entity.Payments;
import com.hotelbooking.paymentservice.model.PaymentResponse;
import com.hotelbooking.paymentservice.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping("make")
	public ResponseEntity<PaymentResponse> makePayment(@Valid @RequestBody Payments payment){
		return new ResponseEntity<PaymentResponse>(paymentService.makePayment(payment),HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Payments> viewPaymentById(@PathVariable int id){
		return new ResponseEntity<Payments>(paymentService.viewPaymentsById(id),HttpStatus.OK);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<Payments>> viewAllPayments(){
		return new ResponseEntity<List<Payments>>(paymentService.viewAllPayments(),HttpStatus.OK);
	}

}
