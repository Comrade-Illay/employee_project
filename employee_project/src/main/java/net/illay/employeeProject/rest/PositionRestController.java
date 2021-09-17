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

import net.illay.employeeProject.model.Position;
import net.illay.employeeProject.service.EmployeeService;


@RestController 
@RequestMapping("/api/position/")
public class PositionRestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Position> getPosition(@PathVariable("id") Long positionId){
		if(positionId == null) {
			return new ResponseEntity<Position>(HttpStatus.BAD_REQUEST);
		}
		
		Position position = this.employeeService.getPositionById(positionId); 
		
		if(position == null) {
			return new ResponseEntity<Position>(HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Position>(position, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Position> savePosition(@Valid @RequestBody Position position){		
		HttpHeaders headers = new HttpHeaders();
		
		if(position == null) {
			return new ResponseEntity<Position>(HttpStatus.BAD_REQUEST); 
		}
		
		this.employeeService.savePosition(position);
		return new ResponseEntity<Position>(position, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method= RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Position> updatePosition(@Valid @RequestBody Position position, UriComponentsBuilder builder){
		HttpHeaders headers = new HttpHeaders();
		
		if(position == null) {
			return new ResponseEntity<Position>(HttpStatus.BAD_REQUEST);
		}
		
		this.employeeService.savePosition(position);
		
		return new ResponseEntity<Position>(position, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method= RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Position> deletePosition(@PathVariable("id") Long id){
		Position position = this.employeeService.getPositionById(id);
		
		if(position == null) {
			return new ResponseEntity<Position>(HttpStatus.NOT_FOUND);
		}
		
		this.employeeService.delete(id);
		
		return new ResponseEntity<Position>(position, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Position> getAllPositions(
			@PageableDefault(value = 10, page = 0, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {

		Page<Position> position = this.employeeService.getAllPositions(pageable);

		return position;

	}
}
