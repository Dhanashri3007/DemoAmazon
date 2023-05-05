package com.product.ServiceIMPL;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.product.Repositry.ProductRepositry;
import com.product.ServiceI.ProductServiceI;
import com.product.imageRepo.ImageRepo;
import com.product.imageUtil.ImageUtil;
import com.product.model.Product;
import com.product.model.ProductImage;

@Service
public class ProductServiceIMPL implements ProductServiceI {
	@Autowired
	ProductRepositry pr;
	
	@Autowired 
	ImageRepo pri;

	@Override
	public List<Product> findAll(int pageNo) {
		Pageable p = PageRequest.of(pageNo, 6);
		
		 List<ProductImage> findAll = pri.findAll();
		 List<Product> list = pr.findAll(p).toList();
		 list.forEach(b->{
		
		 findAll.forEach(a->{
			System.out.println(b.getImageData().getId());
			
		 });
		 });
	  
	
		return pr.findAll(p).toList();
		
	}

	@Override
	public Product save(Product p) {
		return pr.save(p);
	}
	public List<Product> findAllbyBrandName(String brandName){
		
		return pr.findAllBybrandName(brandName);
		 
	}

	@Override
	public List<Product> findAllByPrice(int pageNo, double startprice, double endPrice) {

		Sort sortByPrice = Sort.by(Direction.ASC, "pPrice");
		Pageable p = PageRequest.of(pageNo, 6, sortByPrice);

		return pr.findAllBypPriceBetween(startprice, endPrice, p);
	}
	
	public String uploadImage(MultipartFile file) throws IOException {
		
		ProductImage i=new ProductImage();
		i.setName(file.getOriginalFilename());
		i.setType(file.getContentType());
		i.setImageData(ImageUtil.compressImage(file.getBytes()));
		
		 pri.save(i);
		 return "Image Uploded";
	}
	
	public byte[] downloadImage(String fileName){
        Optional<ProductImage> imageData = pri.findByName(fileName);
        return ImageUtil.decompressImage(imageData.get().getImageData());
    }

	@Override
	public void uploadImagewithProduct(MultipartFile file, Product p) throws Exception {

		ProductImage i=new ProductImage();
		i.setName(file.getOriginalFilename());
		i.setType(file.getContentType());
		i.setImageData(ImageUtil.compressImage(file.getBytes()));

		 p.setImageData(i);
		 pr.save(p);
	}
}
