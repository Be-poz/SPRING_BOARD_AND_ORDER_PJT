package ksy.geshi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GeshiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeshiApplication.class, args);
	}

}
