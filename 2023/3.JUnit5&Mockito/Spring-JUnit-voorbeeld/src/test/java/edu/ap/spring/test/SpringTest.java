package edu.ap.spring.test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.util.*;
import edu.ap.spring.jpa.*;
import edu.ap.spring.service.BusinessService;

@Configuration
@SpringBootTest
public class SpringTest {

    private static BusinessService bService = Mockito.mock(BusinessService.class);
    @Autowired
    private EmployeeRepository repo;

    @Bean
    static Employee getTestEmployee() {
        return new Employee("Philippe", "lecturer");
    }

    @BeforeAll
    // must be static
    public static void init() {
        //phil = new Employee("Philippe", "lecturer");
        Employee phil = getTestEmployee();
    	Mockito.when(bService.getEmployee("Philippe")).thenReturn(phil);
    }
    
    @Test
    public void test1() {

        assumeTrue(5 > 1);
        
        // when
        Employee found = bService.getEmployee("Philippe");
     
        // then
        assertEquals(found.getName(), "Philippe");
     }
    
    @Test
    public void test2() {

    	String status = bService.getEmployee("Philippe").getStatus();
        assertEquals(status, "lecturer");
    }

    @Test
    public void test3() {
        
        Employee phil2 = new Employee("Philippe", "lecturer");
        Employee found = bService.getEmployee("Philippe");
         
        assertEquals(found, phil2);
    }

    @Test
    public void test4() {

        repo.save(getTestEmployee());
        Employee olga = new Employee("Olga", "lecturer");
        repo.save(olga);
        Employee vincent = new Employee("Vincent", "lecturer");
        repo.save(vincent);

        List<Employee> list1 = repo.findAll();
        
        for(Employee l : list1) {
            System.out.println(l.getName());
        }
        List<Employee> list2 = Arrays.asList(getTestEmployee(), olga, vincent);

        assertEquals(list1, list2);
    }
}
