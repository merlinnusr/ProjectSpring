package com.blockbuster.app.controllers;

import com.blockbuster.app.dtos.RentDto;
import com.blockbuster.app.models.Rent;
import com.blockbuster.app.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rents")
public class RentController {
    @Autowired
    private RentService rentService;
    @PostMapping
    public Rent create(@RequestBody RentDto rentDto) {
        return rentService.create(rentDto);
    }
    @GetMapping
    public List<Rent> show() {
        return rentService.all();
    }
    @GetMapping("/{id}")
    public Rent find(@PathVariable Long id) {
        return rentService.find(id);
    }
    @PutMapping("{id}")
    public Rent update(@RequestBody RentDto rentDto, @PathVariable Long id) {
        return rentService.update(rentDto, id);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        rentService.deleteRent(id);
    }
}
