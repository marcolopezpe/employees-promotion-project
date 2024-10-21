package pe.marcolopez.apps.epp.ms.criteria.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeQueryDTO;
import java.util.List;

public interface CriteriaAPI {

  @GetMapping("/eligible-employees")
  ResponseEntity<List<EmployeeQueryDTO>> findEmployeesFromPreviousLevelToNewLevel(@RequestParam("currentLevel") String currentLevel,
                                                                                  @RequestParam("newLevel") String newLevel,
                                                                                  @RequestParam("periodLevel") Integer periodLevel);
}
