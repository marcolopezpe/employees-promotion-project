package pe.marcolopez.apps.epp.ms.command.promotion.api.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.marcolopez.apps.epp.ms.command.promotion.api.EmployeeAPI;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.EmployeeCommandDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.service.EmployeeService;
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

    @Override
    public ResponseEntity<Integer> updateLevel(EmployeeCommandDTO employeeCommandDTO) {
        return ResponseEntity.ok(
            employeeService.updateLevel(employeeCommandDTO.id(), employeeCommandDTO.level())
        );
    }
}
