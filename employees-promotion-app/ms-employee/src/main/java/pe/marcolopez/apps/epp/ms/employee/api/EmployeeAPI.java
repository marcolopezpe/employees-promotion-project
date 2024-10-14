package pe.marcolopez.apps.epp.ms.employee.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.marcolopez.apps.epp.ms.employee.dto.EmployeeQueryDTO;

import java.util.List;

public interface EmployeeAPI {

    @GetMapping
    ResponseEntity<List<EmployeeQueryDTO>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<EmployeeQueryDTO> findById(@PathVariable("id") String id);
}
