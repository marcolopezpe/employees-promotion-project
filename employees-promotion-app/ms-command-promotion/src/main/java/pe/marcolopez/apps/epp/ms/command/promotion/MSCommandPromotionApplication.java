package pe.marcolopez.apps.epp.ms.command.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MSCommandPromotionApplication {

	public static void main(String... args) {
		SpringApplication.run(MSCommandPromotionApplication.class, args);
	}

}
