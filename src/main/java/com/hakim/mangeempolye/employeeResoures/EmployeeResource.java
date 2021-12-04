package com.hakim.mangeempolye.employeeResoures;

import java.util.List;

import com.hakim.mangeempolye.models.Employee;
import com.hakim.mangeempolye.services.EmployeeService;

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

@RequestMapping("/employee")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
@GetMapping("/api/v1/all")
public ResponseEntity <List<Employee>> getAllEmployees(){
    List<Employee> employees=employeeService.findallEmployees();
   return new ResponseEntity<>(employees,HttpStatus.OK);
}

@GetMapping("/api/v1/find/{id}")
public ResponseEntity <Employee> getEmployeeById(@PathVariable("id")Long id){
   Employee employee=employeeService.findEmployeeById(id);
   return new ResponseEntity<>(employee,HttpStatus.OK);
} 
@PostMapping("/api/v1/add")
public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
    Employee newEmployee=employeeService.addEmployee(employee);
    return new ResponseEntity<>(newEmployee,HttpStatus.CREATED);
}
@PutMapping("/api/v1/update")
public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
    Employee newEmployee=employeeService.updateEmployee(employee);
    return new ResponseEntity<>(newEmployee,HttpStatus.OK);
}
@DeleteMapping("/api/v1/delete/{id}")
public ResponseEntity <?> deleteEmployeeById(@PathVariable("id")Long id){
   employeeService.deleteEmploye(id);
   return new ResponseEntity<>(HttpStatus.OK);
} 
}
