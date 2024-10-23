package pe.marcolopez.apps.epp.ms.notification.api.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.marcolopez.apps.epp.ms.notification.api.EmployeeAPI;
import pe.marcolopez.apps.epp.ms.notification.dto.EmployeeCommandDTO;
import pe.marcolopez.apps.epp.ms.notification.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.notification.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeREST implements EmployeeAPI {

  private final EmployeeService employeeService;

  @Override
  public ResponseEntity<List<EmployeeQueryDTO>> findAll() {
    return ResponseEntity.ok(
        employeeService.findAll()
    );
  }

  @Override
  public ResponseEntity<List<EmployeeQueryDTO>> findByCriteria(String currentLevel,
                                                               Integer years,
                                                               Integer certifications,
                                                               Integer projects,
                                                               Integer periodLevel) {
    return ResponseEntity.ok(
        employeeService.findAllByCriteria(currentLevel, years, certifications, projects, periodLevel)
    );
  }

  @Override
  public ResponseEntity<EmployeeQueryDTO> findById(String id) {
    return ResponseEntity.ok(
        employeeService.findById(UUID.fromString(id))
    );
  }

  @Override
  public ResponseEntity<Integer> updateLevel(EmployeeCommandDTO employeeCommandDTO) {
    return ResponseEntity.ok(
        employeeService.updateLevel(employeeCommandDTO.id(), employeeCommandDTO.level())
    );
  }
}
