package pe.marcolopez.apps.epp.ms.consumer.promotion.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "promotions")
public class PromotionDocument {

  @Id
  private UUID id;

  @Field(name = "employee_id")
  private UUID employeeId;

  @Field(name = "employee_firstname")
  private String employeeFirstname;

  @Field(name = "employee_lastname")
  private String employeeLastname;

  @Field(name = "proposed_level")
  private String proposedLevel;

  @Field(name = "status")
  private String status;

  @Field(name = "requestDate")
  private LocalDate requestDate;

  @Field(name = "decision_date")
  private LocalDate decisionDate;

  @Field(name = "leader_id")
  private UUID leaderId;

  @Field(name = "leader_firstname")
  private String leaderFirstname;

  @Field(name = "leader_lastname")
  private String leaderLastname;

  @Field(name = "leader_comments")
  private String leaderComments;

  @Field(name = "period")
  private Integer period;

  @Field(name = "created_at")
  private LocalDate createdAt = LocalDate.now();
}

