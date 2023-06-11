package redis2.films.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import redis2.films.redis.RedisService;

@Controller
public class FilmController {
    @Autowired
    private RedisService redisService;

    @GetMapping("/")
    public String index() {
        return "redirect:/movies";
    }

    @GetMapping("/form")
    public String form() {
        return "movieForm";
    }

    @PostMapping("/movies")
    public String postMessage(@RequestParam("name") String name,
                              @RequestParam("year") String year,
                              @RequestParam("actors") String actors) {
        String key = "movies:" + year + ":" + name;
        System.out.println("key: " + key);
        String[] parts = actors.split(",");
        System.out.println("parts: " + Arrays.toString(parts));
        Arrays.stream(parts).forEach(actor -> redisService.rpush(key, actor));
        redisService.incr("moviescount");
        return "redirect:/movies";
    }

    @GetMapping("/movies")
    public String films(Model model) {
        List<String> movieList = new ArrayList<>();
        Set<String> movieKeys = redisService.keys("movies:*");
        movieKeys.forEach(key -> {
            String[] parts = key.split(":");
            String name = parts[2];
            String year = parts[1];
            List<String> actors = redisService.getList(key);
            String formattedActors = String.join(", ", actors);
            String movie = name + " (" + year + ") Actors: " + formattedActors;
            movieList.add(movie);
        });

        model.addAttribute("movies", movieList);
        model.addAttribute("moviescount", redisService.getKey("moviescount"));

        return "movies";
    }
}

