package pe.marcolopez.apps.epp.ms.notification.service;

import pe.marcolopez.apps.epp.ms.notification.dto.LevelHistoryCommandDTO;
import pe.marcolopez.apps.epp.ms.notification.dto.LevelHistoryQueryDTO;
import java.util.List;
import java.util.UUID;

public interface LevelHistoryService {

  List<LevelHistoryQueryDTO> findAll();

  List<LevelHistoryQueryDTO> findAllByEmployeeId(UUID employeeId);

  LevelHistoryQueryDTO addLevel(LevelHistoryCommandDTO levelHistoryCommandDTO);
}
