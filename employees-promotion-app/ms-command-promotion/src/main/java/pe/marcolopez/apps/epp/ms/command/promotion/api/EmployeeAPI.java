package pe.marcolopez.apps.epp.ms.command.promotion.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.EmployeeCommandDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.EmployeeQueryDTO;
import java.util.List;

public interface EmployeeAPI {

    @GetMapping
    ResponseEntity<List<EmployeeQueryDTO>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<EmployeeQueryDTO> findById(@PathVariable("id") String id);

    @PutMapping("/level/{id}")
    ResponseEntity<Integer> updateLevel(@RequestBody EmployeeCommandDTO employeeCommandDTO);
}
