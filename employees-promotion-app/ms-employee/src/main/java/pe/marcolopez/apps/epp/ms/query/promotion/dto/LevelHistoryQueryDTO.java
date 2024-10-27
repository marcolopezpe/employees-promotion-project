package pe.marcolopez.apps.epp.ms.query.promotion.dto;

import lombok.Builder;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record LevelHistoryQueryDTO(UUID id,
                                   EmployeeQueryDTO employee,
                                   String previousLevel,
                                   String newLevel,
                                   LocalDate changeDate,
                                   UUID evaluatedBy,
                                   String comments) {
}