package pe.marcolopez.apps.epp.ms.consumer.promotion.api.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.marcolopez.apps.epp.ms.consumer.promotion.api.LevelHistoryAPI;
import pe.marcolopez.apps.epp.ms.consumer.promotion.dto.LevelHistoryCommandDTO;
import pe.marcolopez.apps.epp.ms.consumer.promotion.dto.LevelHistoryQueryDTO;
import pe.marcolopez.apps.epp.ms.consumer.promotion.service.LevelHistoryService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/level-histories")
@AllArgsConstructor
public class LevelHistoryREST implements LevelHistoryAPI {

  private final LevelHistoryService levelHistoryService;

  @Override
  public ResponseEntity<List<LevelHistoryQueryDTO>> findAll() {
    return ResponseEntity.ok(
        levelHistoryService.findAll()
    );
  }

  @Override
  public ResponseEntity<List<LevelHistoryQueryDTO>> findAllByEmployeeId(String id) {
    return ResponseEntity.ok(
        levelHistoryService.findAllByEmployeeId(UUID.fromString(id))
    );
  }

  @Override
  public ResponseEntity<LevelHistoryQueryDTO> add(LevelHistoryCommandDTO levelHistoryCommandDTO) {
    return new ResponseEntity<>(levelHistoryService.addLevel(levelHistoryCommandDTO), HttpStatus.CREATED);
  }
}
