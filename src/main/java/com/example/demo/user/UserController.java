package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("{username}/{password}")
	public ResponseEntity<User> login(@PathVariable String username, @PathVariable String password){
		var user = userRepo.findByUP(username, password);
		if(user.isEmpty()) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(user.get(),HttpStatus.FOUND);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserName(@PathVariable String username){
		var user = userRepo.findByUsername(username);
		if(user.isEmpty()) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user.get(), HttpStatus.FOUND);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<User>> getUsers(){
		var users = userRepo.findAll();
		return new ResponseEntity<Iterable<User>>(users, HttpStatus.FOUND);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUser(@PathVariable int id){
		var user = userRepo.findById(id);
		if(user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user.get(), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<User> postUser(@RequestBody User user){
		if(user == null || user.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		userRepo.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> putUser(@PathVariable int id, @RequestBody User user){
		if(user == null || user.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var use = userRepo.findById(id);
		if(use.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		userRepo.save(user);
		return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteUser(@PathVariable int id){
		var user = userRepo.findById(id);
		if(user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		userRepo.delete(user.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
