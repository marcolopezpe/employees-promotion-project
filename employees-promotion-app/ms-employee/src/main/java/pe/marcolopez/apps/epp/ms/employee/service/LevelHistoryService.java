package pe.marcolopez.apps.epp.ms.employee.service;

import pe.marcolopez.apps.epp.ms.employee.dto.LevelHistoryCommandDTO;
import pe.marcolopez.apps.epp.ms.employee.dto.LevelHistoryQueryDTO;
import java.util.List;
import java.util.UUID;

public interface LevelHistoryService {

  List<LevelHistoryQueryDTO> findAll();

  List<LevelHistoryQueryDTO> findAllByEmployeeId(UUID employeeId);

  LevelHistoryQueryDTO addLevel(LevelHistoryCommandDTO levelHistoryCommandDTO);
}
