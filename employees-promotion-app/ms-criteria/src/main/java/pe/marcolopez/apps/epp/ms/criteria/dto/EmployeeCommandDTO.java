package pe.marcolopez.apps.epp.ms.criteria.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record EmployeeCommandDTO(UUID id,
                                 String firstname,
                                 String lastname,
                                 String address,
                                 String email,
                                 String currentLevel,
                                 LocalDate hireDate,
                                 Integer certifications,
                                 Integer productionProjects) {
}
