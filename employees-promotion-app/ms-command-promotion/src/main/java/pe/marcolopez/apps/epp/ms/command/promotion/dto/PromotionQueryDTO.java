package pe.marcolopez.apps.epp.ms.command.promotion.dto;

import lombok.Builder;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record PromotionQueryDTO(UUID id,
                                UUID employeeId,
                                String proposedLevel,
                                String status,
                                LocalDate requestDate,
                                LocalDate decisionDate,
                                UUID leaderId,
                                String leaderComments,
                                Integer period) {
}
