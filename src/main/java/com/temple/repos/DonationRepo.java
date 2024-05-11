package com.temple.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temple.Entities.DonationForm;

public interface DonationRepo extends JpaRepository<DonationForm, Long> {

}
