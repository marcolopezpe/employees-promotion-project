package pe.marcolopez.apps.epp.ms.command.promotion.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_promotion")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private UUID employeeId;
  private String proposedLevel;
  private String status;
  private LocalDate requestDate;
  private LocalDate decisionDate;
  private UUID leaderId;
  private String comments;
  private Integer period;
  private LocalDateTime createdAt;
}
