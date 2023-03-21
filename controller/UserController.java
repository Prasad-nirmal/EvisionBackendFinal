package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.User;
import com.app.exception.ResourceNotFoundException;
import com.app.service.IService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	IService iservice;
	
	@PostMapping("/add")
	public String addUser(@RequestParam ("e") String email,@RequestParam ("p") String pass) {
		 iservice.addUser(email, pass);
		return "Added";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User request) throws ResourceNotFoundException{
		return ResponseEntity.ok().body(iservice.fetchUserByEmailIdAndPassword(request));
	}
}


//	public ResponseEntity<?> loginUser(@RequestBody User user){
//		String email = user.getEmailId();
//		String password = user.getPassword();
//		User userObj = null;
//		if(email != null && password != null) {
//			userObj = iservice.fetchUserByEmailIdAndPassword(email, password);
//		}
//		if(userObj == null) {
//			System.out.println("exception Occured");
//			return null;}
//		return ResponseEntity.ok(userObj);
//		}
//	}
