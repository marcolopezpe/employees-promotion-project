package pe.marcolopez.apps.epp.ms.consumer.promotion.webclient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.marcolopez.apps.epp.ms.consumer.promotion.dto.EmployeeQueryDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeWebClientService {

  @Value("${ms.ms-employee.base-url}")
  private String msEmployeeBaseUrl;

  @Value("${ms.ms-employee.methods.get.find-by-id}")
  private String findById;

  private final WebClient.Builder webClientBuilder;

  @CircuitBreaker(name = "ms-employee", fallbackMethod = "fallbackGetEmployeeById")
  public Mono<EmployeeQueryDTO> getEmployeeById(String employeeId) {
    return webClientBuilder
        .build()
        .get()
        .uri(msEmployeeBaseUrl + findById, employeeId)
        .retrieve()
        .bodyToMono(EmployeeQueryDTO.class);
  }

  public Mono<EmployeeQueryDTO> fallbackGetEmployeeById(String employeeId, Throwable throwable) {
    return Mono.just(
        EmployeeQueryDTO
            .builder()
            .id(UUID.fromString(employeeId))
            .firstname("Firstname not found")
            .lastname("Lastname not found")
            .address("Address not found")
            .email("Email not found")
            .currentLevel("UNKNOWN")
            .hireDate(null)
            .certifications(0)
            .productionProjects(0)
            .build()
    );
  }
}
