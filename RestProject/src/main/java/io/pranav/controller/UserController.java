package io.pranav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pranav.entity.User;
import io.pranav.exception.ResourceNotFoundException;
import io.pranav.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	// get all users

	@GetMapping
	public List<User> getAllUsers() {
		return this.userRepo.findAll();
	}
	// get user by id

	@GetMapping("/{id}")
	public User getUserById(@PathVariable long id) {
		return this.userRepo.findById(id)
				.orElseThrow(() -> 
				new ResourceNotFoundException("User not found with id" + id));
	}

	
	
	
	// create user

	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepo.save(user);
	}
	// update user

	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable long id) {
		User existingUser = this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User does not exists with id" + id));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());

		return this.userRepo.save(existingUser);
	}
	// delete user by id

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id) {
		User existingUser = this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id" + id));
		this.userRepo.delete(existingUser);
		return ResponseEntity.ok().build();
	}
}
