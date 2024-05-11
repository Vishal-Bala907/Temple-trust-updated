package com.temple.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temple.Entities.Products;

public interface FileUploadRepo extends JpaRepository<Products, Long> {
	
}
