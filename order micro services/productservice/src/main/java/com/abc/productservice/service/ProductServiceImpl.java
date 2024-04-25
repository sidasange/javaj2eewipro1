package com.abc.productservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.productservice.entity.Product;
import com.abc.productservice.exception.ResourceNotFoundException;
import com.abc.productservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productrepository;

	@Override
	public Product saveProduct(Product product) {
		productrepository.save(product);
		return product;
	}

	@Override
	public Product getProductById(int productId) {
		Optional<Product> optionalproduct = productrepository.findById(productId);

		if (optionalproduct.isEmpty()) {
			throw new ResourceNotFoundException("Product Not found with id "+productId);

		}
		Product product = optionalproduct.get();
		return product;
	}

	@Override
	public List<Product> getAllProducts() {

		List<Product> products = productrepository.findAll();
		return products;
	}

	@Override
	public Product updateProduct(Product product) {
		Optional<Product> optionalproduct = productrepository.findById(product.getProductId());
		if (optionalproduct.isEmpty()) {
			throw new ResourceNotFoundException("Product Not found with id "+product.getProductId());
		}
		productrepository.save(product);
		return product;

	}

	@Override
	public void deleteProduct(int productId) {
		Optional<Product> optionalproduct = productrepository.findById(productId);
		if (optionalproduct.isEmpty()) {
			throw new ResourceNotFoundException("Product Not found with id "+productId);

		}
		Product product =optionalproduct.get();
		productrepository.delete(product);
	}

	@Override
	public List<Product> getProductByCategory(String categoryName) {
        List<Product> products = productrepository.findByCategory(categoryName);
		return products;
	}

	@Override
	public List<Product> getProductByRange(double minPrice, double maxPrice) {
        List<Product> products = productrepository.filteredProductsinRange(minPrice, maxPrice);
		return products;
	}

}
