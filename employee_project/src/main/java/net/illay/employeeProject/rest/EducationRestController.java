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

import net.illay.employeeProject.model.Education;
import net.illay.employeeProject.service.EmployeeService;


@RestController 
@RequestMapping("/api/education/")
public class EducationRestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Education> getEducation(@PathVariable("id") Long educationId){
		if(educationId == null) {
			return new ResponseEntity<Education>(HttpStatus.BAD_REQUEST);
		}
		
		Education education = this.employeeService.getEducationById(educationId); 
		
		if(education == null) {
			return new ResponseEntity<Education>(HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Education>(education, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Education> saveEducation(@Valid @RequestBody Education education){		
		HttpHeaders headers = new HttpHeaders();
		
		if(education == null) {
			return new ResponseEntity<Education>(HttpStatus.BAD_REQUEST); 
		}
		
		this.employeeService.saveEducation(education);
		return new ResponseEntity<Education>(education, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method= RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Education> updateEducation(@Valid @RequestBody Education education, UriComponentsBuilder builder){
		HttpHeaders headers = new HttpHeaders();
		
		if(education == null) {
			return new ResponseEntity<Education>(HttpStatus.BAD_REQUEST);
		}
		
		this.employeeService.saveEducation(education);
		
		return new ResponseEntity<Education>(education, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method= RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Education> deleteEducation(@PathVariable("id") Long id){
		Education education = this.employeeService.getEducationById(id);
		
		if(education == null) {
			return new ResponseEntity<Education>(HttpStatus.NOT_FOUND);
		}
		
		this.employeeService.delete(id);
		
		return new ResponseEntity<Education>(education, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Education> getAllEducation(
			@PageableDefault(value = 10, page = 0, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {

		Page<Education> education = this.employeeService.getAllEducation(pageable);

		return education;

	}
}
