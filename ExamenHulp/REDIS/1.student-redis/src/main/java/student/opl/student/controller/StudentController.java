package student.opl.student.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import student.opl.student.redis.RedisService;

@Controller
public class StudentController {
    //s122;Max Pooters;s324;Ronny Flex
    @Autowired
    private RedisService redisService;

    @GetMapping("/")
    private String index() {
        return "redirect:/importStudents";
    }

    @GetMapping("/importStudents")
    private String getForm() {
        return "importStudents";
    }

    @PostMapping("/importStudents") 
        public String importStudents(@RequestParam("students") String students) {
            String[] studentArray = students.split(";");
            for (int i = 0; i < studentArray.length; i += 2) {
                if(redisService.getKey(studentArray[i]) == null) {
                    redisService.setKey(studentArray[i], studentArray[i+1]);
                } else {
                    return "notunique";
                }
            }
            return "importStudents";    }

    @GetMapping("/{studentId}")
    private String getStudent(@PathVariable("studentId") String studentId, Model model) {
        model.addAttribute("student", redisService.getKey(studentId));
        return "student";    
    }
}
