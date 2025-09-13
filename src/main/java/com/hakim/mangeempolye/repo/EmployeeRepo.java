package com.hakim.mangeempolye.repo;

import com.hakim.mangeempolye.models.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository <Employee, Long>{
void findEmployeeById(Long id);
void deleteEmployeeById(Long id);
double countByActive(boolean b);
    
}
