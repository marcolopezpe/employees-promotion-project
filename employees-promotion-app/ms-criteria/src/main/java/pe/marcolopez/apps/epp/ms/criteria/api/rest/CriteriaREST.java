package pe.marcolopez.apps.epp.ms.criteria.api.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.marcolopez.apps.epp.ms.criteria.api.CriteriaAPI;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.criteria.service.CriteriaService;
import java.util.List;

@RestController
@RequestMapping("/criteria")
@AllArgsConstructor
public class CriteriaREST implements CriteriaAPI {

  private final CriteriaService criteriaService;

  @Override
  public ResponseEntity<List<EmployeeQueryDTO>> findEmployeesFromPreviousLevelToNewLevel(String currentLevel) {
    return ResponseEntity.ok(
        criteriaService.findEmployeesFromPreviousLevelToNewLevel(currentLevel)
    );
  }
}
