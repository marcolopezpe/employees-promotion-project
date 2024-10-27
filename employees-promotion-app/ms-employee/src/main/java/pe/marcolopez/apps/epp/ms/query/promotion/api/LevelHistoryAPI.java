package pe.marcolopez.apps.epp.ms.query.promotion.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.marcolopez.apps.epp.ms.query.promotion.dto.LevelHistoryCommandDTO;
import pe.marcolopez.apps.epp.ms.query.promotion.dto.LevelHistoryQueryDTO;

import java.util.List;

public interface LevelHistoryAPI {

  @GetMapping
  ResponseEntity<List<LevelHistoryQueryDTO>> findAll();

  @GetMapping("/employee/{employeeId}")
  ResponseEntity<List<LevelHistoryQueryDTO>> findAllByEmployeeId(@PathVariable("employeeId") String id);

  @PostMapping("/employee")
  ResponseEntity<LevelHistoryQueryDTO> add(@RequestBody LevelHistoryCommandDTO levelHistoryCommandDTO);
}
