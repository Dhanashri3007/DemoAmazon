package com.product.Controller;

import java.io.IOException;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.product.Responce.BaseResponce;
import com.product.ServiceI.ProductServiceI;
import com.product.model.Product;
@CrossOrigin("*")
@RestController
public class ProductController {
	
	@Autowired
	ProductServiceI pi;
	
	@GetMapping("/getProductData/{pageNo}")
	public ResponseEntity<BaseResponce<List<Product>>>getProductData(@PathVariable int pageNo)
	{
		
		List<Product> findAll = pi.findAll(pageNo);
		
	BaseResponce<List<Product>> baseResponce =new BaseResponce<>(200,findAll,"All Product Data");
		return new ResponseEntity<BaseResponce<List<Product>>>(baseResponce,HttpStatus.OK);
	}
	@GetMapping("/getProductData/{pageNo}/{startprice}/{endPrice}")
	public ResponseEntity<BaseResponce<List<Product>>>getProductDatabyPrice( @PathVariable int pageNo,@PathVariable double startprice ,@PathVariable double endPrice)
	{
		List<Product> findAll = pi.findAllByPrice(pageNo, startprice , endPrice);
	BaseResponce<List<Product>> baseResponce =new BaseResponce<>(200,findAll,"All Product Data");
		return new ResponseEntity<BaseResponce<List<Product>>>(baseResponce,HttpStatus.OK);
	}
	
	@GetMapping("/getDataByName/{brandName}")
	public ResponseEntity<BaseResponce<List<Product>>>getDataByName(@PathVariable String brandName)
	{
		
		List <Product> bNameList=pi.findAllbyBrandName(brandName);
		BaseResponce<List<Product>> baseResponce=new BaseResponce<>(200,bNameList,"all +"+brandName+"+Product");
		return new ResponseEntity<BaseResponce<List<Product>>>(baseResponce, HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public void uploadImage(@RequestPart("productImage")MultipartFile file) throws IOException{
		pi.uploadImage(file);
	}
	
	@PostMapping(value = {"/upload2"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public void uploadImageWithProduct(@RequestPart("imageData")MultipartFile file,@RequestPart Product p) throws IOException{
		try {
			pi.uploadImagewithProduct(file,p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@GetMapping("/download/{fileName}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
		byte[] image = pi.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
	}
	
	@PostMapping("/insertProductData")
	public ResponseEntity<BaseResponce<Product>> postProductData(@RequestBody Product p)
	{
		Product product =pi.save(p);
		
		BaseResponce<Product> baseResponce=new BaseResponce<>(200,product,"Insert Data Successfully");
		return new ResponseEntity<BaseResponce<Product>>(baseResponce,HttpStatus.OK);
	}
	
}
