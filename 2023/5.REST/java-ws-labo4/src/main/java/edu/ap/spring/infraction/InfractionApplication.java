package edu.ap.spring.infraction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import edu.ap.spring.infraction.jpa.Infraction;
import edu.ap.spring.infraction.jpa.InfractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;

@SpringBootApplication
public class InfractionApplication implements CommandLineRunner {

    @Value("classpath:static/infractions.json")
    public InputStream data;

    @Autowired
    private InfractionRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(InfractionApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Infraction[] infractions = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JaxbAnnotationModule());
        try {
            infractions = objectMapper.readValue(data, Infraction[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Infraction infraction : infractions) {
            repository.save(infraction);
        }
    }
}
