package pe.marcolopez.apps.epp.ms.command.promotion.service;

import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionEvaluateEmployeeDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionEvaluateLeaderDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionQueryDTO;
import java.util.List;
import java.util.UUID;

public interface PromotionService {

  List<PromotionQueryDTO> findAllByLeaderIdAndStatus(UUID leaderId, String status);

  List<PromotionQueryDTO> findAllByLeaderId(UUID leaderId);

  List<PromotionQueryDTO> findByEmployeeId(UUID employeeId);

  PromotionQueryDTO evaluateByEmployee(PromotionEvaluateEmployeeDTO promotionEvaluateEmployeeDTO);

  PromotionQueryDTO evaluateByLeader(PromotionEvaluateLeaderDTO promotionEvaluateLeaderDTO);
}
