package com.blockbuster.app.repositories;

import com.blockbuster.app.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
