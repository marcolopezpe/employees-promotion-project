package pe.marcolopez.apps.epp.ms.employee.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.employee.dto.LevelHistoryCommandDTO;
import pe.marcolopez.apps.epp.ms.employee.dto.LevelHistoryQueryDTO;
import pe.marcolopez.apps.epp.ms.employee.entity.LevelHistoryEntity;

@Mapper(
        componentModel = "spring"
)
public interface LevelHistoryMapper {

  LevelHistoryQueryDTO toQueryDTO(LevelHistoryEntity levelHistoryEntity);

  LevelHistoryEntity toEntity(LevelHistoryQueryDTO levelHistoryQueryDTO);

  LevelHistoryEntity toEntity(LevelHistoryCommandDTO levelHistoryCommandDTO);
}
