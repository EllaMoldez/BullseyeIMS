package ca.bullseye.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EntityScan("ca.bullseye.ims.model")
@EnableJpaRepositories("ca.bullseye.ims.repositories")
@SpringBootApplication
public class BullseyeIMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(BullseyeIMSApplication.class, args);
	}
}
