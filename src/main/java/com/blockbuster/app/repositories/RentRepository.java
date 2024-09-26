package com.blockbuster.app.repositories;

import com.blockbuster.app.models.Rent;
import com.blockbuster.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByUserId(Long userId);
}
