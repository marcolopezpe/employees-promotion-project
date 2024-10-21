package pe.marcolopez.apps.epp.ms.criteria.service;

import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeQueryDTO;
import java.util.List;

public interface CriteriaService {

  List<EmployeeQueryDTO> findEmployeesFromPreviousLevelToNewLevel(String currentLevel, String newLevel, Integer periodLevel);
}
