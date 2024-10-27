package pe.marcolopez.apps.epp.ms.consumer.promotion.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Value("${microservices.ms-employee.baseUrl}")
  private String msEmployeeBaseUrl;

  @Bean
  public WebClient.Builder getWebClientBuilder() {
    return WebClient.builder().baseUrl(msEmployeeBaseUrl);
  }
}
