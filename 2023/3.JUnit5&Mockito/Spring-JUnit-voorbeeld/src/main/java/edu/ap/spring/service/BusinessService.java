package edu.ap.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ap.spring.jpa.Employee;
import edu.ap.spring.jpa.EmployeeRepository;

@Service
public class BusinessService {
	
  @Autowired
  private EmployeeRepository repo;
       
  public Employee getEmployee(String name) {
        
    return repo.findByName(name);
  }

  public void saveEmployee(Employee emp) {
        
    repo.save(emp);
  }
}
