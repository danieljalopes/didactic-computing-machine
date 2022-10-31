package com.example.flyway_demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.flyway_demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByFirstName(String firstName);

}
