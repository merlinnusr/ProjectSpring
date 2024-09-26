package com.blockbuster.app.services;

import com.blockbuster.app.dtos.MovieDto;
import com.blockbuster.app.dtos.RentDto;
import com.blockbuster.app.exceptions.RentNotFoundException;
import com.blockbuster.app.models.Movie;
import com.blockbuster.app.models.Rent;
import com.blockbuster.app.models.User;
import com.blockbuster.app.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public Movie create(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setDirector(movieDto.getDirector());
        movie.setReleasedAt(movieDto.getReleasedAt());
        movie.setRate(movieDto.getRate());
        movie.setDuration(movieDto.getDuration());
        movie.setStock(movieDto.getStock());
        return movieRepository.save(movie);
    }
    public List<Movie> all() {
        return movieRepository.findAll();
    }
    public Movie find(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RentNotFoundException("Renta no encontrada"));
    }

    public Movie update(MovieDto movieDto, Long id){
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RentNotFoundException("No se encuentra la pelicula"));

        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setDirector(movieDto.getDirector());
        movie.setReleasedAt(movieDto.getReleasedAt());
        movie.setRate(movieDto.getRate());
        movie.setDuration(movieDto.getDuration());
        movie.setStock(movieDto.getStock());

        return movie;
    }
}
