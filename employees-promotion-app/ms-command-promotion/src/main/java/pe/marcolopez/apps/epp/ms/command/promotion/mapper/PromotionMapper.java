package pe.marcolopez.apps.epp.ms.command.promotion.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionEvaluateEmployeeDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionEvaluateLeaderDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionQueryDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.entity.PromotionEntity;
import pe.marcolopez.apps.epp.ms.kafka.event.PromotionEmployeeEvent;
import pe.marcolopez.apps.epp.ms.kafka.event.PromotionLeaderEvent;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

  PromotionQueryDTO toQueryDTO(PromotionEntity promotionEntity);

  PromotionEmployeeEvent toEventEmployee(PromotionQueryDTO promotionQueryDTO);

  PromotionLeaderEvent toEventLeader(PromotionQueryDTO promotionQueryDTO);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateFromDTO(PromotionEvaluateEmployeeDTO promotionEvaluateEmployeeDTO,
                     @MappingTarget PromotionEntity promotionEntity);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateFromDTO(PromotionEvaluateLeaderDTO promotionEvaluateLeaderDTO,
                     @MappingTarget PromotionEntity promotionEntity);
}
