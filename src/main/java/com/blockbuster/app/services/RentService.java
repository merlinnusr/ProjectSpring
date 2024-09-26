package com.blockbuster.app.services;

import com.blockbuster.app.dtos.RentDto;
import com.blockbuster.app.exceptions.RentNotFoundException;
import com.blockbuster.app.models.Movie;
import com.blockbuster.app.models.Rent;
import com.blockbuster.app.models.User;
import com.blockbuster.app.repositories.MovieRepository;
import com.blockbuster.app.repositories.RentRepository;
import com.blockbuster.app.repositories.UserRepository;
import com.blockbuster.app.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class RentService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private JwtUtil jwtUtil;

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
    public List<Rent> all() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();

            // Query the database to find the user ID by the username
            User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("No existe el usuario"));  // Assume your User entity has a getId method

            return rentRepository.findByUserId(user.getId());

        }
        return Collections.emptyList();
    }
    public Rent find(Long id) {
        return rentRepository.findById(id).orElseThrow(() -> new RentNotFoundException("Renta no encontrada"));
    }

    public Rent update(RentDto rentDto, Long id){
        Movie movie = findMovieById(DEFAULT_MOVIE_ID);
        User user = findUserById(rentDto.getUserId());

        var rent = rentRepository.findById(id).orElseThrow(() -> new RentNotFoundException("Renta no encontrada"));
        rent.setRentAt(LocalDateTime.now());
        rent.setDevolutionAt(LocalDateTime.now().plusDays(2));
        rent.setUser(user);
        rent.setMovie(Collections.singletonList(movie));
        rent.setAmount(DEFAULT_AMOUNT);
        rent.setStatus("Rented");
        return rent;
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