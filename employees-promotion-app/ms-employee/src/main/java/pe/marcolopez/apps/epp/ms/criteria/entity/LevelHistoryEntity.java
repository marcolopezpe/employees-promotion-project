package pe.marcolopez.apps.epp.ms.criteria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.marcolopez.apps.epp.ms.criteria.util.EmployeeLevel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_level_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LevelHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(targetEntity = EmployeeEntity.class)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
    private EmployeeLevel previousLevel;
    private EmployeeLevel newLevel;
    @Column(columnDefinition = "DATE")
    private LocalDate changeDate;
    private UUID evaluatedBy;
    private String comments;
    private LocalDateTime createdAt;
}