package examen1.examen1.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import examen1.examen1.jpa.Student;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/student";
    }

    @GetMapping("/student")
    public String StudentPage() {
        return "addStudent";
    }

    @GetMapping("/listStudent")
    public String getStudents(Model model) {
        model.addAttribute("students", studentRepository.findAllByOrderByLastnameAsc());
        return "listStudent";
    }
    
    @PostMapping("/addStudent")
    public String addStudent(@RequestParam String firstname, @RequestParam String lastname, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateofbirth, @RequestParam String studyprogram) {
        List<Student> students = studentRepository.findByFirstnameAndLastname(firstname, lastname);
        if (students.isEmpty()) {
            Student student = new Student(firstname, lastname, dateofbirth, studyprogram);
            System.out.println(student.getFirstname());
            studentRepository.save(student);
        }
        return "redirect:/listStudent";
    }
}
