package com.blockbuster.app.services;

import com.blockbuster.app.dtos.EmployeeDto;
import com.blockbuster.app.dtos.RentDto;
import com.blockbuster.app.models.Employee;
import com.blockbuster.app.models.Movie;
import com.blockbuster.app.models.Rent;
import com.blockbuster.app.models.User;
import com.blockbuster.app.repositories.EmployeeRepository;
import com.blockbuster.app.repositories.MovieRepository;
import com.blockbuster.app.repositories.RentRepository;
import com.blockbuster.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class RentService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentRepository rentRepository;

    private static final Long DEFAULT_MOVIE_ID = 1L;
    private static final BigDecimal DEFAULT_AMOUNT = BigDecimal.valueOf(1000);

    public Rent create(RentDto rentDto) {
        validateRentDto(rentDto);
        Movie movie = findMovieById(DEFAULT_MOVIE_ID);
        User user = findUserById(rentDto.getUserId());

        Rent rent = new Rent();
        rent.setRentAt(LocalDateTime.now());
        rent.setDevolutionAt(LocalDateTime.now().plusDays(2));
        rent.setUser(user);
        rent.setMovie(Collections.singletonList(movie));
        rent.setAmount(DEFAULT_AMOUNT);
        rent.setStatus("Rented");

        return rentRepository.save(rent);
    }

    private Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    private void validateRentDto(RentDto rentDto) {
        if (rentDto == null || rentDto.getUserId() == null) {
            throw new RuntimeException("Invalid RentDto");
        }
    }
}