package spring.rest.rest2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.InputStream;
import spring.rest.rest2.jpa.Infraction;
import spring.rest.rest2.jpa.InfractionRepository;

@SpringBootApplication
public class Rest2Application implements CommandLineRunner {

    @Value("classpath:static/infractions.json")
	public InputStream data;

	@Autowired
	private InfractionRepository infractionRepository;

	public static void main(String[] args) {
		SpringApplication.run(Rest2Application.class, args);
	}

	@Override
	public void run(String... args) {
		Infraction[] infractions = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JaxbAnnotationModule());
		try {
			infractions = mapper.readValue(data, Infraction[].class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (Infraction infraction : infractions) {
			infractionRepository.save(infraction);
		}

	}

}
