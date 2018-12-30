package dash.swastik.student_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class PlatformStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformStudentApplication.class, args);
	}

}

