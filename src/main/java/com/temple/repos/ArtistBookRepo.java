package com.temple.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temple.Entities.BookArtist;

public interface ArtistBookRepo extends JpaRepository<BookArtist, Integer> {
	BookArtist findByDate(String date);
}
