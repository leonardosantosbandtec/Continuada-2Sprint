package bandtec.com.Projetoindividualsprint3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
public class ProjetoIndividualSprint3Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoIndividualSprint3Application.class, args);
	}

}
