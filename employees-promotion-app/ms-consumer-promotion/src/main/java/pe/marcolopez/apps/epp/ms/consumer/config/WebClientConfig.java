package pe.marcolopez.apps.epp.ms.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  @LoadBalanced
  public WebClient.Builder getWebClientBuilder() {
    return WebClient.builder();
  }
}