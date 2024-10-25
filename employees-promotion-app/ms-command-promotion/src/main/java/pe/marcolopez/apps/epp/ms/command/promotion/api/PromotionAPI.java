package pe.marcolopez.apps.epp.ms.command.promotion.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionEvaluateEmployeeDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionEvaluateLeaderDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionQueryDTO;

import java.util.List;

public interface PromotionAPI {

  @GetMapping("/leader/{leaderId}")
  ResponseEntity<List<PromotionQueryDTO>> findAllByLeaderId(@PathVariable("leaderId") String leaderId);

  @GetMapping("/leader/{leaderId}/status/{status}")
  ResponseEntity<List<PromotionQueryDTO>> findAllByLeaderIdAndStatus(@PathVariable("leaderId") String leaderId,
                                                                     @PathVariable("status") String status);

  @PutMapping("/evaluate/employee")
  ResponseEntity<?> evaluateEmployee(@RequestBody PromotionEvaluateEmployeeDTO promotionEvaluateEmployeeDTO);

  @PostMapping("/evaluate/leader")
  ResponseEntity<?> evaluateLeader(@RequestBody PromotionEvaluateLeaderDTO promotionEvaluateLeaderDTO);
}
