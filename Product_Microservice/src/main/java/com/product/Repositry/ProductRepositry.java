package com.product.Repositry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.model.Product;
import com.product.model.ProductImage;
@Repository
public interface ProductRepositry extends JpaRepository<Product, Integer>{

	List findAllBypPriceBetween( double startprice, double endPrice, Pageable p);

	List<Product> findAllBybrandName(String brandName);

	


  

}
