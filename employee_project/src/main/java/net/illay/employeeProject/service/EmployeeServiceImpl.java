package net.illay.employeeProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.illay.employeeProject.model.Education;
import net.illay.employeeProject.model.Employee;
import net.illay.employeeProject.model.Passport;
import net.illay.employeeProject.model.Position;
import net.illay.employeeProject.repository.EducationRepository;
import net.illay.employeeProject.repository.EmployeeRepository;
import net.illay.employeeProject.repository.PassportRepository;
import net.illay.employeeProject.repository.PositionRepository;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PassportRepository passportRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired
	private EducationRepository educationRepository;
	
    public Employee getById(Long id) {
		log.info("IN EmployeeServiceImpl getByID {}", id);
		return employeeRepository.findById(id).get();
	}

	public void save(Employee employee) {
		log.info("IN EmployeeServiceImpl save {}", employee);
		employeeRepository.save(employee);

	}

	public void delete(Long id) {
		log.info("IN EmployeeServiceImpl delete {}", id);
		employeeRepository.deleteById(id);
	}

	public Page<Employee> getAll(Pageable pageable) {	
		List<Employee> employee = employeeRepository.findAll(pageable).stream()
                .collect(Collectors.toList());				                                 
		return new PageImpl<Employee>(employee);
	}
	
	public Passport getPassportById(Long id) {
		log.info("IN PassportServiceImpl getByID {}", id);
		return passportRepository.findById(id).get();
	}

	public void savePassport(Passport passport) {
		log.info("IN PassportServiceImpl save {}", passport);
		passportRepository.save(passport);

	}

	public void deletePassport(Long id) {
		log.info("IN PassportServiceImpl delete {}", id);
		passportRepository.deleteById(id);
	}

	public Page<Passport> getAllPassports(Pageable pageable) {	
		List<Passport> passport = passportRepository.findAll(pageable).stream()
                .collect(Collectors.toList());				                                 
		return new PageImpl<Passport>(passport);
	}
	
	
	
	public Position getPositionById(Long id) {
		log.info("IN PositionServiceImpl getByID {}", id);
		return positionRepository.findById(id).get();
	}

	public void savePosition(Position position) {
		log.info("IN PositionServiceImpl save {}", position);
		positionRepository.save(position);

	}

	public void deletePosition(Long id) {
		log.info("IN PositionServiceImpl delete {}", id);
		positionRepository.deleteById(id);
	}

	public Page<Position> getAllPositions(Pageable pageable) {	
		List<Position> position = positionRepository.findAll(pageable).stream()
                .collect(Collectors.toList());				                                 
		return new PageImpl<Position>(position);
	}
	
	

	public Education getEducationById(Long id) {
		log.info("IN EducationServiceImpl getByID {}", id);
		return educationRepository.findById(id).get();
	}

	public void saveEducation(Education education) {
		log.info("IN EducationServiceImpl save {}", education);
		educationRepository.save(education);

	}

	public void deleteEducation(Long id) {
		log.info("IN EducationServiceImpl delete {}", id);
		educationRepository.deleteById(id);
	}

	public Page<Education> getAllEducation(Pageable pageable) {	
		List<Education> education = educationRepository.findAll(pageable).stream()
                .collect(Collectors.toList());				                                 
		return new PageImpl<Education>(education);
	}

}









