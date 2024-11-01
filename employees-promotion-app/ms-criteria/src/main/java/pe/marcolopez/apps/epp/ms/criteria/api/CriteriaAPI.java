package pe.marcolopez.apps.epp.ms.criteria.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pe.marcolopez.apps.epp.ms.criteria.dto.CriteriaQueryDTO;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeCommandDTO;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeQueryDTO;
import java.util.List;

public interface CriteriaAPI {

  @GetMapping("/eligible-employees")
  ResponseEntity<List<EmployeeQueryDTO>> findEligibleEmployees(@RequestParam("currentLevel") String currentLevel,
                                                               @RequestParam("newLevel") String newLevel,
                                                               @RequestParam("periodLevel") Integer periodLevel);

  @PostMapping("/notify-eligible-employee")
  ResponseEntity<Object> notifyEligibleEmployee(@RequestBody EmployeeCommandDTO employeeCommandDTO);

  @GetMapping
  ResponseEntity<List<CriteriaQueryDTO>> findCriteriaQueries();

  @GetMapping("/level/{level}")
  ResponseEntity<List<CriteriaQueryDTO>> findCriteriaQueriesByLevel(@PathVariable("level") String level);
}
