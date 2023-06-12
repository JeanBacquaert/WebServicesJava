package h1.oef1.springboot.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Grade {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstName;

	@Column
    private String lastName;

    @Column
    private int grade;

	public Grade() {}
    
    public Grade(String firstName, String lastName, int grade) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.grade = grade;
    }
}
