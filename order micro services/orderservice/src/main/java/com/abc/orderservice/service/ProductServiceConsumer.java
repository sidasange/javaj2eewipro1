package com.abc.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.orderservice.model.Product;

@FeignClient(name="PRODUCTSERVICE",fallbackFactory = ProductServiceFallBackFactory.class)
public interface ProductServiceConsumer {

	 @GetMapping("/product/{id}")
     Product getProductById(@PathVariable("id") int productId);
	 
	 @GetMapping("/product/data")
      String getProductData();	 
	 
}