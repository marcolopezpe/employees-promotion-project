package pe.marcolopez.apps.epp.ms.criteria.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeQueryDTO;
import java.util.List;

@FeignClient(name = "ms-employee", fallback = EmployeeProxyService.FallBack.class)
public interface EmployeeProxyService {

  @GetMapping("/employees/{employeeId}")
  EmployeeQueryDTO findById(@PathVariable("employeeId") String id);

  @GetMapping("/employees/criteria")
  List<EmployeeQueryDTO> findByCriteria(@RequestParam("currentLevel") String currentLevel,
                                        @RequestParam("years") Integer years,
                                        @RequestParam("certifications") Integer certifications,
                                        @RequestParam("projects") Integer projects);

  @Slf4j
  @Component
  class FallBack implements EmployeeProxyService {

    @Override
    public EmployeeQueryDTO findById(String id) {
      return EmployeeQueryDTO
          .builder()
          .id(null)
          .firstname("Firstname not found")
          .lastname("Lastname not found")
          .address("Address not found")
          .email("Email not found")
          .currentLevel("UNKNOWN")
          .hireDate(null)
          .certifications(0)
          .productionProjects(0)
          .build();
    }

    @Override
    public List<EmployeeQueryDTO> findByCriteria(String currentLevel,
                                                 Integer years,
                                                 Integer certifications,
                                                 Integer projects) {
      return List.of(
          EmployeeQueryDTO
              .builder()
              .id(null)
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
}
