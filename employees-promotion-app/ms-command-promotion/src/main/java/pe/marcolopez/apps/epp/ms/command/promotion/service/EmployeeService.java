package pe.marcolopez.apps.epp.ms.command.promotion.service;

import pe.marcolopez.apps.epp.ms.command.promotion.dto.EmployeeQueryDTO;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    List<EmployeeQueryDTO> findAll();

    EmployeeQueryDTO findById(UUID id);

    int updateLevel(UUID id, String level);
}
