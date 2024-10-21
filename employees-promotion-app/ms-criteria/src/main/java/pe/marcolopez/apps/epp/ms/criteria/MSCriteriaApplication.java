package pe.marcolopez.apps.epp.ms.criteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MSCriteriaApplication {

	public static void main(String... args) {
		SpringApplication.run(MSCriteriaApplication.class, args);
	}

}
