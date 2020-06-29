package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserServiceController {
   private static Map<String, User> userRepo = new HashMap<>();
   static {
	   User user1 = new User();
	   user1.setId("1");
	   user1.setName("Shivam");
      userRepo.put(user1.getId(), user1);
      
      User user2 = new User();
      user2.setId("2");
      user2.setName("Shubham");
      userRepo.put(user2.getId(), user2);
   }
   
   @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
      userRepo.remove(id);
      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody User product) { 
      userRepo.remove(id);
      product.setId(id);
      userRepo.put(id, product);
      return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/user", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody User product) {
      userRepo.put(product.getId(), product);
      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
   }
   
   @RequestMapping(value = "/user")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(userRepo.values(), HttpStatus.OK);
   }
}