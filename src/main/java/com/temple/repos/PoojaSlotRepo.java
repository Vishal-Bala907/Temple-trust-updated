package com.temple.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temple.Entities.PoojaSlot;

public interface PoojaSlotRepo extends JpaRepository<PoojaSlot, Long> {
	PoojaSlot findByDateAndTime(String date , String time);
}
