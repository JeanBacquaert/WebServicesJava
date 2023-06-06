package edu.ap.spring.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ap.spring.RedisApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ap.spring.redis.RedisService;

@Controller
public class RedisController {

    public static final String CHANNEL = "edu:ap:redis";

    private final List<String> redisMessages = new ArrayList<>();

    @Autowired
    private RedisService service;

    @GetMapping("/")
    public String index() {
        return "redirect:/messages";
    }

    @PostMapping("/messages")
    public String postMessage(@RequestParam("message") String message) {
        this.service.sendMessage(CHANNEL, message);
        return "redirect:/messages";
    }

    @GetMapping("/form")
    public String messageForm() {
        return "messageForm";
    }

    @GetMapping("/messages")
    public String messages(Model model) {

        model.addAttribute("messages", redisMessages);

        return "messages";
    }

    // messaging
    public void onMessage(String message) {
        this.redisMessages.add(message);
    }
}