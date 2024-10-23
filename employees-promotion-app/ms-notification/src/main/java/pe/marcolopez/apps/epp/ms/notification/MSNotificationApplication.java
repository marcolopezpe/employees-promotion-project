package pe.marcolopez.apps.epp.ms.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MSNotificationApplication {

	public static void main(String... args) {
		SpringApplication.run(MSNotificationApplication.class, args);
	}

}
