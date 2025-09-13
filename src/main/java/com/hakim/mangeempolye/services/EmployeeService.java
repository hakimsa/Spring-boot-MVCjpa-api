package com.hakim.mangeempolye.services;

import java.util.List;

import com.hakim.mangeempolye.models.Employee;
import com.hakim.mangeempolye.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

@Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    public Employee addEmployee(Employee employee){

        return employeeRepo.save(employee);
    }
    public Employee updateEmployee(Employee employee){

        return employeeRepo.save(employee);
    }
    public List<Employee>findallEmployees(){

        return employeeRepo.findAll();
    }

    public Employee findEmployeeById(Long id){
   
   return employeeRepo.getById(id);

    }
    public double getActiveEmployeeCount() {
    return employeeRepo.countByActive(true);
}


    public void deleteEmploye(Long id){
   employeeRepo.deleteEmployeeById(id);
    }
}
