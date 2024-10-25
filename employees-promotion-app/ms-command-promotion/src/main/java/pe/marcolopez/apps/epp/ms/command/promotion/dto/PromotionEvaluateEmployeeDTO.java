package pe.marcolopez.apps.epp.ms.command.promotion.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PromotionEvaluateEmployeeDTO(UUID promotionId,
                                           UUID employeeId,
                                           @Pattern(regexp = "ACCEPTED|DECLINED")
                                           String status) {
}
