package api;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class main {

    public static void main(String[] args) {
        SpringApplication.run(main.class, args);
    }
    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            System.out.println("Hello world!");
        };
    }
}