package net.illay.employeeProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.illay.employeeProject.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	Page<Employee> findAll(Pageable pageable);
  
}
