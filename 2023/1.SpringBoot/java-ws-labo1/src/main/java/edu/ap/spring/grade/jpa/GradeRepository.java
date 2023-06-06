package edu.ap.spring.grade.jpa;

import org.springframework.data.repository.CrudRepository;

public interface GradeRepository extends CrudRepository<Grade, Long> {

    Grade findGradeByFirstNameAndLastName(String firstName, String lastName);
}