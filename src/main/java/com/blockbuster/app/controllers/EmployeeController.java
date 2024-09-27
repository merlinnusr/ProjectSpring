package com.blockbuster.app.controllers;

import com.blockbuster.app.dtos.ClientDto;
import com.blockbuster.app.dtos.EmployeeDto;
import com.blockbuster.app.models.Client;
import com.blockbuster.app.models.Employee;
import com.blockbuster.app.services.ClientService;
import com.blockbuster.app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping
    public Employee createClient(@RequestBody EmployeeDto employeeDto) {
        return employeeService.create(employeeDto);
    }
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }
    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return employeeService.update(employeeDto, id);
    }
}
