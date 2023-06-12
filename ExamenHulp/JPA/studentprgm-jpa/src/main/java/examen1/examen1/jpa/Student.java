package examen1.examen1.jpa;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="dateofbirth")
    private LocalDate dateofbirth;

    @Column(name="studyprogram")
    private String studyprogram;

    public Student() {
    }

    public Student(String firstname, String lastname, LocalDate dateofbirth, String studyprogram) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.studyprogram = studyprogram;
    }

    @Override
    public String toString() {
        return lastname + " " + firstname + " " + dateofbirth + " " + studyprogram;
    }
}
