package pe.marcolopez.apps.epp.ms.criteria.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.criteria.dto.LevelHistoryCommandDTO;
import pe.marcolopez.apps.epp.ms.criteria.dto.LevelHistoryQueryDTO;
import pe.marcolopez.apps.epp.ms.criteria.entity.LevelHistoryEntity;

@Mapper(
        componentModel = "spring"
)
public interface LevelHistoryMapper {

  LevelHistoryQueryDTO toQueryDTO(LevelHistoryEntity levelHistoryEntity);

  LevelHistoryEntity toEntity(LevelHistoryQueryDTO levelHistoryQueryDTO);

  LevelHistoryEntity toEntity(LevelHistoryCommandDTO levelHistoryCommandDTO);
}
