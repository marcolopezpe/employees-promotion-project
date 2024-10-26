package pe.marcolopez.apps.epp.ms.command.promotion.api.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.marcolopez.apps.epp.ms.command.promotion.api.PromotionAPI;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionEvaluateEmployeeDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionEvaluateLeaderDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionQueryDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.service.PromotionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/promotions")
@AllArgsConstructor
public class PromotionREST implements PromotionAPI {

  private final PromotionService promotionService;

  @Override
  public ResponseEntity<List<PromotionQueryDTO>> findAllByLeaderId(String leaderId) {
    return ResponseEntity.ok(
        promotionService.findAllByLeaderId(UUID.fromString(leaderId))
    );
  }

  @Override
  public ResponseEntity<List<PromotionQueryDTO>> findAllByLeaderIdAndStatus(String leaderId, String status) {
    return ResponseEntity.ok(
        promotionService.findAllByLeaderIdAndStatus(
            UUID.fromString(leaderId), status
        )
    );
  }

  @Override
  public ResponseEntity<List<PromotionQueryDTO>> findByEmployeeId(String employeeId) {
    return ResponseEntity.ok(
        promotionService.findByEmployeeId(UUID.fromString(employeeId))
    );
  }

  @Override
  public ResponseEntity<?> evaluateEmployee(PromotionEvaluateEmployeeDTO promotionEvaluateEmployeeDTO) {
    return ResponseEntity.ok(
        promotionService.evaluateByEmployee(promotionEvaluateEmployeeDTO)
    );
  }

  @Override
  public ResponseEntity<?> evaluateLeader(PromotionEvaluateLeaderDTO promotionEvaluateLeaderDTO) {
    return ResponseEntity.ok(
        promotionService.evaluateByLeader(promotionEvaluateLeaderDTO)
    );
  }
}
