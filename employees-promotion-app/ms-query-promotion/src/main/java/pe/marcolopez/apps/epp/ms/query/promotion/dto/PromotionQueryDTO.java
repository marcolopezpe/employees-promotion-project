package pe.marcolopez.apps.epp.ms.query.promotion.dto;

import lombok.Builder;

@Builder
public record PromotionQueryDTO (String id,
                                 String employeeId,
                                 String employeeFirstname,
                                 String employeeLastname,
                                 String proposedLevel,
                                 String status,
                                 String requestDate,
                                 String decisionDate,
                                 String leaderId,
                                 String leaderFirstname,
                                 String leaderLastname,
                                 Integer period) {
}
