package pe.marcolopez.apps.epp.ms.criteria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_criteria")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriteriaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String level;
  private String description;
  private String type;
  private Integer expectedValue;
  private LocalDateTime createdAt;
}
