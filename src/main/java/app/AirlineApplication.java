package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableSwagger2
@EnableAsync
@EnableScheduling
public class AirlineApplication {
	public static void main(String[] args) {
		SpringApplication.run(AirlineApplication.class, args);
		System.out.println("___________________");
		System.out.println("Готово к работе");
	}
}