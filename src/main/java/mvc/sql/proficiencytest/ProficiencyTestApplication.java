package mvc.sql.proficiencytest;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ProficiencyTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProficiencyTestApplication.class, args);
	}

	@Bean(initMethod = "migrate")
	public Flyway flyway(final Environment environment) {
		return Flyway.configure()
				.dataSource(
						environment.getProperty("spring.datasource.url"),
						environment.getProperty("spring.datasource.username"),
						environment.getProperty("spring.datasource.password"))
				.locations(environment.getProperty("spring.flyway.locations"))
				.load();
	}

}
