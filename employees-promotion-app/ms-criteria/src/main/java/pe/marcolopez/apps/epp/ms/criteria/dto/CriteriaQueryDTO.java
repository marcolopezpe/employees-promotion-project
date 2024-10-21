package pe.marcolopez.apps.epp.ms.criteria.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CriteriaQueryDTO(UUID id,
                               String level,
                               String description,
                               String type,
                               String expectedValue) {
}
