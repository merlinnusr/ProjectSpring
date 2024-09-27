package com.blockbuster.app.controllers;

import com.blockbuster.app.dtos.MovieDto;
import com.blockbuster.app.models.Movie;
import com.blockbuster.app.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @PostMapping
    public Movie create(@RequestBody MovieDto movieDto) {
        return movieService.create(movieDto);
    }
    @GetMapping
    public List<Movie> show() {
        return movieService.all();
    }
    @GetMapping("/{id}")
    public Movie find(@PathVariable Long id) {
        return movieService.find(id);
    }
    @PutMapping("{id}")
    public Movie update(@RequestBody MovieDto movieDto, @PathVariable Long id) {
        return movieService.update(movieDto, id);
    }
    @DeleteMapping
    public void delete(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

}
