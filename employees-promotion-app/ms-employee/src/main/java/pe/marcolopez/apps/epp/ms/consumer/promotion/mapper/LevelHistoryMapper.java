package pe.marcolopez.apps.epp.ms.consumer.promotion.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.consumer.promotion.dto.LevelHistoryCommandDTO;
import pe.marcolopez.apps.epp.ms.consumer.promotion.dto.LevelHistoryQueryDTO;
import pe.marcolopez.apps.epp.ms.consumer.promotion.entity.LevelHistoryEntity;

@Mapper(
        componentModel = "spring"
)
public interface LevelHistoryMapper {

  LevelHistoryQueryDTO toQueryDTO(LevelHistoryEntity levelHistoryEntity);

  LevelHistoryEntity toEntity(LevelHistoryQueryDTO levelHistoryQueryDTO);

  LevelHistoryEntity toEntity(LevelHistoryCommandDTO levelHistoryCommandDTO);
}
