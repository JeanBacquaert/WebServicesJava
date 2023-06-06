package edu.ap.spring;

import edu.ap.spring.controller.RedisController;
import edu.ap.spring.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SpringBootApplication
public class RedisApplication {

    @Autowired
    private RedisService service;

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new ChannelTopic(RedisController.CHANNEL));

        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RedisController controller) {
        return new MessageListenerAdapter(controller, "onMessage");
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return (args) -> {

            // empty db
            this.service.flushDb();

            // messaging
            this.service.sendMessage(RedisController.CHANNEL, "Hello from Spring Boot");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
