package examen1.examen1.jpa;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "student")
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

    public long getId() {
        return this.id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public LocalDate getDateofbirth() {
        return this.dateofbirth;
    }

    public void setStudyprogram(String studyprogram) {
        this.studyprogram = studyprogram;
    }

    public String getStudyprogram() {
        return this.studyprogram;
    }

    @Override
    public String toString() {
        return lastname + " " + firstname + " " + dateofbirth + " " + studyprogram;
    }

}
