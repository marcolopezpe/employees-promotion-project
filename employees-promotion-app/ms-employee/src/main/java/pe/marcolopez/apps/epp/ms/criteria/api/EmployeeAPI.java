package pe.marcolopez.apps.epp.ms.criteria.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeCommandDTO;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeQueryDTO;

import java.util.List;

public interface EmployeeAPI {

  @GetMapping
  ResponseEntity<List<EmployeeQueryDTO>> findAll();

  @GetMapping("/eligible-employees")
  ResponseEntity<List<EmployeeQueryDTO>> findByCriteria(@RequestParam("currentLevel") String currentLevel,
                                                        @RequestParam("years") Integer years,
                                                        @RequestParam("certifications") Integer certifications,
                                                        @RequestParam("projects") Integer projects,
                                                        @RequestParam("periodLevel") Integer periodLevel);

  @GetMapping("/{id}")
  ResponseEntity<EmployeeQueryDTO> findById(@PathVariable("id") String id);

  @PutMapping("/level/{id}")
  ResponseEntity<Integer> updateLevel(@RequestBody EmployeeCommandDTO employeeCommandDTO);
}
