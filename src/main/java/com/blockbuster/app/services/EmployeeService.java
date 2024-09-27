package com.blockbuster.app.services;
import com.blockbuster.app.dtos.EmployeeDto;
import com.blockbuster.app.models.Employee;
import com.blockbuster.app.models.User;
import com.blockbuster.app.repositories.EmployeeRepository;
import com.blockbuster.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    public Employee create(EmployeeDto employeeDto){
        User user = new User();
        user.setEmail(employeeDto.getEmail());
        user.setPassword(employeeDto.getPassword());
        userRepository.save(user);

        Employee employee = new Employee();
        employee.setUser(user);
        employee.setName(employeeDto.getName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPhone(employeeDto.getPhone());
        employee.setHiredAt(employeeDto.getHiredAt());
        employee.setCharge(employeeDto.getCharge());
        employeeRepository.save(employee);
        return employee;
    }

    public Employee update(EmployeeDto employeeDto, Long id) {
        var employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        User user = employee.getUser();
        user.setEmail(employeeDto.getEmail());
        user.setPassword(employeeDto.getPassword());
        userRepository.save(user);
        employee.setUser(user);
        employee.setName(employeeDto.getName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPhone(employeeDto.getPhone());
        employee.setHiredAt(employeeDto.getHiredAt());
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
}
