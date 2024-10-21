package pe.marcolopez.apps.epp.ms.command.promotion.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EmployeeCommandDTO (UUID id,
                                  String level) {
}
