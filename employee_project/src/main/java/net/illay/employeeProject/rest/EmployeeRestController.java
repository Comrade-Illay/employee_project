package net.illay.employeeProject.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import net.illay.employeeProject.model.Employee;
import net.illay.employeeProject.service.EmployeeService;


@RestController 
@RequestMapping("/api/employee/")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long employeeId){
		if(employeeId == null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
		
		Employee employee = this.employeeService.getById(employeeId); 
		
		if(employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){		
		HttpHeaders headers = new HttpHeaders();
		
		if(employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST); 
		}
		
		this.employeeService.save(employee);
		return new ResponseEntity<Employee>(employee, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method= RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee, UriComponentsBuilder builder){
		HttpHeaders headers = new HttpHeaders();
		
		if(employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
		
		this.employeeService.save(employee);
		
		return new ResponseEntity<Employee>(employee, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method= RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id){
		Employee employee = this.employeeService.getById(id);
		
		if(employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		
		this.employeeService.delete(id);
		
		return new ResponseEntity<Employee>(employee, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Employee> getAllEmployee(
			@PageableDefault(value = 10, page = 0, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {

		Page<Employee> employee = this.employeeService.getAll(pageable);

		return employee;

	}
}
