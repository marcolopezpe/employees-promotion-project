package pe.marcolopez.apps.epp.ms.command.promotion.dto;

import lombok.Builder;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record EmployeeQueryDTO (UUID id,
                                String firstname,
                                String lastname,
                                String address,
                                String email,
                                String currentLevel,
                                LocalDate hireDate,
                                Integer certifications,
                                Integer productionProjects,
                                UUID leaderId) {
}
