package pe.marcolopez.apps.epp.ms.consumer.promotion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.marcolopez.apps.epp.ms.consumer.promotion.document.PromotionDocument;
import pe.marcolopez.apps.epp.ms.consumer.promotion.dto.EmployeeQueryDTO;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

  @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
  @Mapping(target = "employeeId", source = "employee.id")
  @Mapping(target = "employeeFirstname", source = "employee.firstname")
  @Mapping(target = "employeeLastname", source = "employee.lastname")
  @Mapping(target = "leaderId", source = "leader.id")
  @Mapping(target = "leaderFirstname", source = "leader.firstname")
  @Mapping(target = "leaderLastname", source = "leader.lastname")
  @Mapping(target = "proposedLevel", source = "proposedLevel")
  @Mapping(target = "status", source = "status")
  @Mapping(target = "requestDate",
      expression = "java(pe.marcolopez.apps.epp.ms.consumer.promotion.util.DateUtil.convertStringToLocalDate("
                   + "requestDate, \"yyyy-MM-dd\"))")
  @Mapping(target = "decisionDate",
      expression = "java(pe.marcolopez.apps.epp.ms.consumer.promotion.util.DateUtil.convertStringToLocalDate("
                   + "decisionDate, \"yyyy-MM-dd\"))")
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
