package com.blockbuster.app.controllers;

import com.blockbuster.app.dtos.ClientDto;
import com.blockbuster.app.dtos.EmployeeDto;
import com.blockbuster.app.models.Client;
import com.blockbuster.app.models.Employee;
import com.blockbuster.app.services.ClientService;
import com.blockbuster.app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping
    public Employee createClient(@RequestBody EmployeeDto employeeDto) {
        return employeeService.create(employeeDto);
    }
}
