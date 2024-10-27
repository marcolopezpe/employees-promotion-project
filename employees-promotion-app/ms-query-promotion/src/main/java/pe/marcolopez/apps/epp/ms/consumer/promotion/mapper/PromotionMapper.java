package pe.marcolopez.apps.epp.ms.consumer.promotion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.marcolopez.apps.epp.ms.consumer.promotion.document.PromotionDocument;
import pe.marcolopez.apps.epp.ms.consumer.promotion.dto.EmployeeQueryDTO;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "employeeId", source = "employee.id")
  @Mapping(target = "employeeFirstname", source = "employee.firstname")
  @Mapping(target = "employeeLastname", source = "employee.lastname")
  @Mapping(target = "leaderId", source = "leader.id")
  @Mapping(target = "leaderFirstname", source = "leader.firstname")
  @Mapping(target = "leaderLastname", source = "leader.lastname")
  @Mapping(target = "proposedLevel", source = "proposedLevel")
  @Mapping(target = "status", source = "status")
  @Mapping(target = "requestDate", source = "requestDate")
  @Mapping(target = "decisionDate", source = "decisionDate")
  @Mapping(target = "leaderComments", source = "leaderComments")
  @Mapping(target = "period", source = "period")
  PromotionDocument toPromotionDocument(
      EmployeeQueryDTO employee,
      EmployeeQueryDTO leader,
      String proposedLevel,
      String status,
      String requestDate,
      String decisionDate,
      String leaderComments,
      Integer period
  );
}
