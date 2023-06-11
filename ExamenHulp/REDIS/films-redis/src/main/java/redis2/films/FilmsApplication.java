package redis2.films;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import redis2.films.redis.RedisService;


@SpringBootApplication
public class FilmsApplication {

	@Autowired
	private RedisService redisService;

		@Bean
  	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {

			// empty db
			this.redisService.flushDb();

		};
  }



	public static void main(String[] args) {
		SpringApplication.run(FilmsApplication.class, args);
	}

}
