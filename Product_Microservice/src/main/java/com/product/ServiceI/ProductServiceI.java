package com.product.ServiceI;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.product.model.Product;

public interface ProductServiceI {

	 public List<Product> findAll(int pageNo);

	public Product save(Product p);

	public List<Product> findAllByPrice(int pageNo,double startprice ,double endPrice);

	public List<Product> findAllbyBrandName(String brandName);
	public String uploadImage(MultipartFile file) throws IOException;
	
	public byte[] downloadImage(String fileName);

	public void uploadImagewithProduct(MultipartFile file, Product p) throws Exception;
	
}
