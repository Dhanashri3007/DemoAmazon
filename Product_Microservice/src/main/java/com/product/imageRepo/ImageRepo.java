package com.product.imageRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.ProductImage;

public interface ImageRepo extends JpaRepository<ProductImage, Integer>{
	Optional<ProductImage> findByName(String fileName);
}
