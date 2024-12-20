package pe.marcolopez.apps.epp.ms.employee.service;

import pe.marcolopez.apps.epp.ms.employee.dto.EmployeeQueryDTO;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {

  List<EmployeeQueryDTO> findAll();

  List<EmployeeQueryDTO> findAllByCriteria(String currentLevel,
                                           Integer years,
                                           Integer certifications,
                                           Integer projects,
                                           Integer periodLevel);

  EmployeeQueryDTO findById(UUID id);

  int updateLevel(UUID id, String level);
}
