package com.temple.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temple.Entities.BookHall;

public interface BookHallRepo extends JpaRepository<BookHall, Long> {
	BookHall findByDate(String date);
}
