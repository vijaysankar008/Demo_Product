package com.nandha.Nandha.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nandha.Nandha.Dto.Product;
import com.nandha.Nandha.advice.ProductNotFoundException;

@Controller
public class MainController {
	private static Map<String, Product> box=new HashMap<>();
	static Product product=new Product();
	static {
		Product honey=new Product();
		honey.setId("1");
		honey.setName("Dabur Honey");
		box.put(honey.getId(),honey);
		
		Product ghee=new Product();
		ghee.setId("2");
		ghee.setName("GRB Ghee");
		box.put(ghee.getId(), ghee);
	}
	@GetMapping("/box")
	public ResponseEntity<Collection<Product>> getBox(){
		Collection<Product> list=box.values();
		//System.out.println(list);
		HttpHeaders head=new HttpHeaders();
		head.add("info", "This will shows the list of Products");
		ResponseEntity<Collection<Product>> entity =new ResponseEntity<>(list, head,HttpStatus.OK);
		return entity;
	}
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") String id){
		Product currentProduct=box.get(id);
		ResponseEntity<Product> entity= new ResponseEntity<>(currentProduct,HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/createBox")
	public ResponseEntity<String> createBox(@RequestBody Product product){
//		this.product.setId(product.getId());
//		this.product.setName(product.getName());
		box.put(product.getId(), product);
		HttpHeaders head=new HttpHeaders();
		String s="Product is created Succesfully";
		head.add("info", "Here Object Creation is Happenning");
		ResponseEntity<String> entity=new ResponseEntity<>("Object is created Successfully",head,HttpStatus.CREATED);
		return entity;
	}

	@PutMapping("/editBox/{id}")
	public ResponseEntity<String> editBox(@PathVariable("id") String id,@RequestBody Product product){
		if(!box.containsKey(id))
			throw new ProductNotFoundException();
		box.remove(id);
		//System.out.println(box);
		box.put(product.getId(), product);
		HttpHeaders headers=new HttpHeaders();
		headers.add("Info", "Here Data Has been Updated");
		ResponseEntity<String> entity=new ResponseEntity<>("Product Updated Successfully",headers,HttpStatus.ACCEPTED);
		return entity;
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable String id){
		box.remove(id);
		ResponseEntity<String> entity=new ResponseEntity<>("The Product is succefully Deleted", HttpStatus.OK);
		return entity;
	}
	
}
