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

import net.illay.employeeProject.model.Passport;
import net.illay.employeeProject.service.EmployeeService;


@RestController 
@RequestMapping("/api/passport/")
public class PassportRestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Passport> getPassport(@PathVariable("id") Long passportId){
		if(passportId == null) {
			return new ResponseEntity<Passport>(HttpStatus.BAD_REQUEST);
		}
		
		Passport passport = this.employeeService.getPassportById(passportId); 
		
		if(passport == null) {
			return new ResponseEntity<Passport>(HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Passport>(passport, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Passport> savePassport(@Valid @RequestBody Passport passport){		
		HttpHeaders headers = new HttpHeaders();
		
		if(passport == null) {
			return new ResponseEntity<Passport>(HttpStatus.BAD_REQUEST); 
		}
		
		this.employeeService.savePassport(passport);
		return new ResponseEntity<Passport>(passport, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method= RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Passport> updatePassport(@Valid @RequestBody Passport passport, UriComponentsBuilder builder){
		HttpHeaders headers = new HttpHeaders();
		
		if(passport == null) {
			return new ResponseEntity<Passport>(HttpStatus.BAD_REQUEST);
		}
		
		this.employeeService.savePassport(passport);
		
		return new ResponseEntity<Passport>(passport, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method= RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Passport> deletePassport(@PathVariable("id") Long id){
		Passport passport = this.employeeService.getPassportById(id);
		
		if(passport == null) {
			return new ResponseEntity<Passport>(HttpStatus.NOT_FOUND);
		}
		
		this.employeeService.delete(id);
		
		return new ResponseEntity<Passport>(passport, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Passport> getAllPassports(
			@PageableDefault(value = 10, page = 0, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {

		Page<Passport> passport = this.employeeService.getAllPassports(pageable);

		return passport;

	}
}
