package pe.marcolopez.apps.epp.ms.criteria.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EmployeeCommandDTO (UUID id,
                                  String level) {
}
