package com.example.demo.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	Optional<User> findByUP(String username, String password);
}
