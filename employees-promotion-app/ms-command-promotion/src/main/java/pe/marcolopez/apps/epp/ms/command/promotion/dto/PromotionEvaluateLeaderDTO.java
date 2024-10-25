package pe.marcolopez.apps.epp.ms.command.promotion.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record PromotionEvaluateLeaderDTO(UUID promotionId,
                                         UUID employeeId,
                                         UUID leaderId,
                                         String status,
                                         String comments) {
}
