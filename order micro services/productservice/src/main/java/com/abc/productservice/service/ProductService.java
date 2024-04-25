package com.abc.productservice.service;

import java.util.List;

import com.abc.productservice.entity.Product;

public interface ProductService {
	
	Product saveProduct(Product product);
	
	Product getProductById(int productId);
	
	List<Product> getAllProducts();
	
	Product updateProduct(Product product);
	
	void deleteProduct(int productId);
	
	List<Product> getProductByCategory(String categoryName);
	
	List<Product> getProductByRange(double minPrice,double maxPrice);

	

}
