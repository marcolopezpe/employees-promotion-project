package pe.marcolopez.apps.epp.ms.notification.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.notification.dto.LevelHistoryCommandDTO;
import pe.marcolopez.apps.epp.ms.notification.dto.LevelHistoryQueryDTO;
import pe.marcolopez.apps.epp.ms.notification.entity.LevelHistoryEntity;

@Mapper(
        componentModel = "spring"
)
public interface LevelHistoryMapper {

  LevelHistoryQueryDTO toQueryDTO(LevelHistoryEntity levelHistoryEntity);

  LevelHistoryEntity toEntity(LevelHistoryQueryDTO levelHistoryQueryDTO);

  LevelHistoryEntity toEntity(LevelHistoryCommandDTO levelHistoryCommandDTO);
}
