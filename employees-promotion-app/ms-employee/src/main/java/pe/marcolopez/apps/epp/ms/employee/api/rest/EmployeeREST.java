package pe.marcolopez.apps.epp.ms.employee.api.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.marcolopez.apps.epp.ms.employee.api.EmployeeAPI;
import pe.marcolopez.apps.epp.ms.employee.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.employee.service.EmployeeService;

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
    public ResponseEntity<EmployeeQueryDTO> findById(String id) {
        return ResponseEntity.ok(
                employeeService.findById(UUID.fromString(id))
        );
    }
}
