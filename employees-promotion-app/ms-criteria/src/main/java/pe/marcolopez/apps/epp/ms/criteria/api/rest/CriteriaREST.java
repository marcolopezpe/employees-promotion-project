package pe.marcolopez.apps.epp.ms.criteria.api.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.marcolopez.apps.epp.ms.criteria.api.CriteriaAPI;
import pe.marcolopez.apps.epp.ms.criteria.dto.CriteriaQueryDTO;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeCommandDTO;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.criteria.service.CriteriaService;
import java.util.List;

@RestController
@RequestMapping("/criteria")
@AllArgsConstructor
public class CriteriaREST implements CriteriaAPI {

  private final CriteriaService criteriaService;

  @Override
  public ResponseEntity<List<EmployeeQueryDTO>> findEligibleEmployees(String currentLevel,
                                                                      String newLevel,
                                                                      Integer periodLevel) {
    return ResponseEntity.ok(
        criteriaService.findEligibleEmployees(currentLevel, newLevel, periodLevel)
    );
  }

  @Override
  public ResponseEntity<Object> notifyEligibleEmployee(EmployeeCommandDTO employeeCommandDTO) {
    criteriaService.notifyEligibleEmployee(employeeCommandDTO);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<List<CriteriaQueryDTO>> findCriteriaQueries() {
    return ResponseEntity.ok(
        criteriaService.findCriteriaQueries()
    );
  }

  @Override
  public ResponseEntity<List<CriteriaQueryDTO>> findCriteriaQueriesByLevel(String level) {
    return ResponseEntity.ok(
        criteriaService.findCriteriaQueriesByLevel(level)
    );
  }
}
