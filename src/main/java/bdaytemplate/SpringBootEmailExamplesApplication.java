package bdaytemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootEmailExamplesApplication  {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmailExamplesApplication.class, args);
	}
}
