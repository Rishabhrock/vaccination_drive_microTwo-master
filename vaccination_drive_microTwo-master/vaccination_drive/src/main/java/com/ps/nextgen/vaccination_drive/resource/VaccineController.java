package com.ps.nextgen.vaccination_drive.resource;

import java.util.List;

import com.ps.nextgen.vaccination_drive.service.IEmployeeService;
import com.ps.nextgen.vaccination_drive.repository.impl.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/vaccine-api")
public class VaccineController {

	
	@Autowired
	private IEmployeeService service;
	
	
	@GetMapping("/allEmployee")
	public ResponseEntity<Object> getAllEmployeeInfo(){
		
		List<EmployeeDetails> list =  service.getAllEmployee();
		
		if(list.size() > 0) return new ResponseEntity<Object>(list, HttpStatus.OK);
		else return new ResponseEntity<Object>("No Employees in DB", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/employeeById/{eid}")
	public EmployeeDetails getEmployeeById(@PathVariable int eid) {
		
		return (EmployeeDetails) service.getEmployeeById(eid);
	}
	
	@PostMapping("/addEmployee")
	public EmployeeDetails addEmployee(@RequestBody EmployeeDetails e){
		return service.addEmployee(e);
	} 
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable int id) {
		 if(service.deleteEmployee(id)) {
			 return new ResponseEntity<Object>("Employee Deleted Successfully", HttpStatus.OK);
			 
		 }else {
			 return new ResponseEntity<Object>("Employee Not Deleted Successfully", HttpStatus.NOT_FOUND);
		 }
	}
	
	@PutMapping("/updateEmployee/{id}")
	public  ResponseEntity<Object> updateEmployee(@RequestBody EmployeeDetails emp,@PathVariable int id) {
		
		if(service.updateEmployee(emp,id)) {
			return new ResponseEntity<Object>("Employee Updated Successfully", HttpStatus.OK);
		}else return new ResponseEntity<Object>("Employee Not Updated Successfully", HttpStatus.NOT_ACCEPTABLE);
	}

	
	@GetMapping("/allSingleDoseEmployee")
	public ResponseEntity<Object> getAllSingleDoseEmployeeInfo(){
		
		List<EmployeeDetails> list =  service.getSingleDoseEmployeeDetails();
		
		if(list.size() > 0) return new ResponseEntity<Object>(list, HttpStatus.OK);
		else return new ResponseEntity<Object>("No Employees in DB", HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/allDoubleDoseEmployee")
	public ResponseEntity<Object> getAllDoubleDoseEmployeeInfo(){
		
		List<EmployeeDetails> list =  service.getDoubleDoseEmployeeDetails();
		
		if(list.size() > 0) return new ResponseEntity<Object>(list, HttpStatus.OK);
		else return new ResponseEntity<Object>("No Employees in DB", HttpStatus.NO_CONTENT);
	}
	
}
