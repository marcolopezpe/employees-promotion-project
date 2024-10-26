package pe.marcolopez.apps.epp.ms.command.promotion.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.LevelHistoryCommandDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.LevelHistoryQueryDTO;

@FeignClient(name = "ms-employee", fallback = EmployeeProxyService.FallBack.class)
public interface EmployeeProxyService {

  @GetMapping("/employees/{employeeId}")
  EmployeeQueryDTO findById(@PathVariable("employeeId") String id);

  @PutMapping("/employees/{employeeId}/level/{level}")
  Integer updateLevel(@PathVariable("employeeId") String employeeId,
                      @PathVariable("level") String level);

  @PostMapping("/level-histories/employee")
  LevelHistoryQueryDTO add(@RequestBody LevelHistoryCommandDTO levelHistoryCommandDTO);

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
    public Integer updateLevel(String employeeId, String level) {
      return 0;
    }

    @Override
    public LevelHistoryQueryDTO add(LevelHistoryCommandDTO levelHistoryCommandDTO) {
      return LevelHistoryQueryDTO
          .builder()
          .id(null)
          .employee(null)
          .previousLevel("Previous level not found")
          .newLevel("New level not found")
          .changeDate(null)
          .evaluatedBy(null)
          .comments("Comments not found")
          .build();
    }
  }
}
