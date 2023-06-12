package h1.oef1.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import h1.oef1.springboot.jpa.Grade;
import h1.oef1.springboot.jpa.GradeRepository;

@Controller
public class GradeController {
    
    @Autowired
    private GradeRepository gradeRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("grades", gradeRepository.findAll());
        return "list";
    }

    @GetMapping("/{firstName}/{lastName}")
    public String getDetail(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, Model model) {
        
        model.addAttribute("grade", gradeRepository.findByFirstNameAndLastName(firstName, lastName));
        return "detail";
    }

    @GetMapping("/grade")
    public String grade() {
        return "gradeForm";
    }

    @PostMapping("/grade")
    public String saveGrade(String firstName, String lastName, int grade) {
       
        gradeRepository.save(new Grade(firstName, lastName, grade));
        return "redirect:/list";
    }
}
