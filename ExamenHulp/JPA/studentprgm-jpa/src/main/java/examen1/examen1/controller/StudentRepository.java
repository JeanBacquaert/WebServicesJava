package examen1.examen1.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import examen1.examen1.jpa.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstnameAndLastname(String firstname, String lastname);

    List<Student> findAllByOrderByLastnameAsc();
}
