package com.blockbuster.app.controllers;

import com.blockbuster.app.dtos.EmployeeDto;
import com.blockbuster.app.dtos.RentDto;
import com.blockbuster.app.models.Employee;
import com.blockbuster.app.models.Rent;
import com.blockbuster.app.services.EmployeeService;
import com.blockbuster.app.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rents")
public class RentController {
    @Autowired
    private RentService rentService;
    @PostMapping
    public Rent createRent(@RequestBody RentDto rentDto) {
        return rentService.create(rentDto);
    }
}
