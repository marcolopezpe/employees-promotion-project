package pe.marcolopez.apps.epp.ms.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_employee")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String currentLevel;
    private Integer periodLevel;
    @Column(columnDefinition = "DATE")
    private LocalDate hireDate;
    private Integer certifications;
    private Integer productionProjects;
    private UUID leaderId;
    private LocalDateTime createdAt = LocalDateTime.now();
}
