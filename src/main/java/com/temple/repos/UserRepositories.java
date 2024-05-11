package com.temple.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temple.Entities.Devotee;


public interface UserRepositories extends JpaRepository<Devotee, Long> {
	Devotee	findByEmail(String email);
	Devotee findByUsername(String name);

}
