package com.hakim.mangeempolye.controllers.employeeResoures;
import java.util.List;
import com.hakim.mangeempolye.models.Employee;
import com.hakim.mangeempolye.monitoring.CustomMetrics;
import com.hakim.mangeempolye.services.EmployeeService;

import io.micrometer.core.instrument.Timer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/employee")
public class EmployeeResource {
    
    private final EmployeeService employeeService;
    private final CustomMetrics metrics;
   
    public EmployeeResource(EmployeeService employeeService, CustomMetrics metrics) {
        this.employeeService = employeeService;
        this.metrics = null;
    }
@GetMapping("/api/v1/all")
public ResponseEntity <List<Employee>> getAllEmployees(){
      Timer.Sample sample = metrics.startTimer();

    try {
        List<Employee> employees = employeeService.findallEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);

    } finally {
        metrics.stopTimer(sample, "/employee/api/v1/all", "200");
    }
}

@GetMapping("/api/v1/find/{id}")
public ResponseEntity <Employee> getEmployeeById(@PathVariable("id")Long id){
  Timer.Sample sample = metrics.startTimer();

    try {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);

    } finally {
        metrics.stopTimer(sample, "/employee/api/v1/find", "200");
    }
} 
@PostMapping("/api/v1/add")
public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
      Timer.Sample sample = metrics.startTimer();
      try {
        Employee newEmployee = employeeService.addEmployee(employee);

        metrics.incrementEmployeeCreated("success");

        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);

    } catch (Exception e) {

        metrics.incrementEmployeeCreated("error");

        throw e;

    } finally {
        metrics.stopTimer(sample, "/employee/api/v1/add", "201");
    }
}
@PutMapping("/api/v1/update/{id}")
public ResponseEntity<Employee> updateEmployee(
        @PathVariable("id") Long id,
        @RequestBody Employee employee) {
    

   Timer.Sample sample = metrics.startTimer();

    try {
        employee.setId(id);
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

    } finally {
        metrics.stopTimer(sample, "/employee/api/v1/update", "200");
    }
}
@DeleteMapping("/api/v1/delete/{id}")
public ResponseEntity <?> deleteEmployeeById(@PathVariable("id")Long id){
   employeeService.deleteEmploye(id);
  Timer.Sample sample = metrics.startTimer();

    try {
        employeeService.deleteEmploye(id);
        return new ResponseEntity<>(HttpStatus.OK);

    } finally {
        metrics.stopTimer(sample, "/employee/api/v1/delete", "200");
    }
}}
