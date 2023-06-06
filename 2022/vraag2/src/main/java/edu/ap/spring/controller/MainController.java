package edu.ap.spring.controller;

import edu.ap.spring.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class MainController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/importStudents")
    public String importStudents() {
        return "importstudents";
    }

    @PostMapping("/importStudents")
    public String importStudents(@RequestParam("students") String students) {
//        import CSV string into redis
        String[] studentArray = students.split(";");
        for (int i = 0; i < studentArray.length; i += 2) {
            redisService.getKey(studentArray[i]);
            if (redisService.getKey(studentArray[i]) == null) {
                redisService.setKey(studentArray[i], studentArray[i + 1]);
            } else {
                return "notunique";
            }
        }

        return "importstudents";
    }

    @GetMapping(value="/{studentId}")
    public String getBalance(@PathVariable("studentId") String studentId,
                             Model model) {
        model.addAttribute("student", redisService.getKey(studentId));
        return "student";
    }
}
