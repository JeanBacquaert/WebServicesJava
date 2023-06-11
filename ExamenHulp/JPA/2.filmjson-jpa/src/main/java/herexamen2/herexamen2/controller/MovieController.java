package herexamen2.herexamen2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import herexamen2.herexamen2.jpa.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

    private final Resource jsonFile;
    private final ObjectMapper objectMapper;

    @Autowired
    public MovieController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.jsonFile = new ClassPathResource("static/movies.json");
    }

    private List<Movie> loadMovies() throws IOException {
        try (InputStream inputStream = jsonFile.getInputStream()) {
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Movie.class);
            return objectMapper.readValue(inputStream, collectionType);
        }
    }

    @GetMapping("/movies")
    public String index() {
        return "movies";
    }

    @GetMapping("/movies/formActor")
    public String getForm() {
        return "moviesFormActor";
    }

    @GetMapping("/movies/formYear")
    public String getFormYear() {
        return "moviesFormYear";
    }

    @GetMapping("/movies/actor")
    public String getMoviesByActor(@RequestParam("actor") String actor, Model model) {
        try {
            List<Movie> movies = loadMovies();
            List<Movie> moviesByActor = new ArrayList<>();
            for (Movie movie : movies) {
                String[] actors = movie.getActors().split(", ");
                for (String movieActor : actors) {
                    if (movieActor.trim().equals(actor)) {
                        moviesByActor.add(movie);
                        break;
                    }
                }
            }
            model.addAttribute("movies", moviesByActor);
            return "moviesResult";
        } catch (Exception e) {
            System.out.println(e);
            return "movies";
        }
    }

    @GetMapping("/movies/year")
    public String getMoviesByYear(@RequestParam("year") String year, Model model) {
        try {
            List<Movie> movies = loadMovies();
            List<Movie> moviesByYear = new ArrayList<>();
            for (Movie movie : movies) {
                if (movie.getYear().equals(year)) {
                    moviesByYear.add(movie);
                }
            }
            model.addAttribute("movies", moviesByYear);
            return "moviesResult";
        } catch (Exception e) {
            System.out.println(e);
            return "movies";
        }
    }
}
