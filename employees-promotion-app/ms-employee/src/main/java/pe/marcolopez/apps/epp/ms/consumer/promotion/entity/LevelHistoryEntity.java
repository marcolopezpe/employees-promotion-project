package pe.marcolopez.apps.epp.ms.consumer.promotion.entity;

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
@Table(name = "tb_level_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LevelHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID employeeId;
    private String previousLevel;
    private String newLevel;
    @Column(columnDefinition = "DATE")
    private LocalDate changeDate;
    private UUID evaluatedBy;
    private String comments;
    private LocalDateTime createdAt = LocalDateTime.now();
}
