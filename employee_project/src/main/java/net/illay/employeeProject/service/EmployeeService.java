package net.illay.employeeProject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.illay.employeeProject.model.Education;
import net.illay.employeeProject.model.Employee;
import net.illay.employeeProject.model.Passport;
import net.illay.employeeProject.model.Position;

public interface EmployeeService {
	
	Employee getById(Long id);	
	void save(Employee employee); 	
	void delete(Long id); 	
	Page<Employee> getAll(Pageable pageable);
	
	Passport getPassportById(Long id);
	void savePassport(Passport passport); 	
	void deletePassport(Long id); 	
	Page<Passport> getAllPassports(Pageable pageable);
	
	Position getPositionById(Long id);
	void savePosition(Position position); 	
	void deletePosition(Long id); 	
	Page<Position> getAllPositions(Pageable pageable);
	
	Education getEducationById(Long id);
	void saveEducation(Education education); 	
	void deleteEducation(Long id); 	
	Page<Education> getAllEducation(Pageable pageable);
}
