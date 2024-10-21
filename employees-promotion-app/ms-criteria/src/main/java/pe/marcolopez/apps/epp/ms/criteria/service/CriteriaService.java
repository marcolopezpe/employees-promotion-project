package pe.marcolopez.apps.epp.ms.criteria.service;

import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeCommandDTO;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeQueryDTO;
import java.util.List;

public interface CriteriaService {

  List<EmployeeQueryDTO> findEligibleEmployees(String currentLevel, String newLevel, Integer periodLevel);

  void notifyEligibleEmployee(EmployeeCommandDTO employeeCommandDTO);
}
