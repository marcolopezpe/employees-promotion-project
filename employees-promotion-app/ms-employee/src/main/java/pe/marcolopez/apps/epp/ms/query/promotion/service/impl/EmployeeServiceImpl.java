package pe.marcolopez.apps.epp.ms.query.promotion.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.query.promotion.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.query.promotion.mapper.EmployeeMapper;
import pe.marcolopez.apps.epp.ms.query.promotion.repository.EmployeeRepository;
import pe.marcolopez.apps.epp.ms.query.promotion.service.EmployeeService;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeMapper employeeMapper;
  private final EmployeeRepository employeeRepository;

  @Override
  public List<EmployeeQueryDTO> findAll() {
    return employeeRepository
        .findAll()
        .stream()
        .map(employeeMapper::toQueryDTO)
        .toList();
  }

  @Override
  public List<EmployeeQueryDTO> findAllByCriteria(String currentLevel,
                                                  Integer years,
                                                  Integer certifications,
                                                  Integer projects,
                                                  Integer periodLevel) {
    return employeeRepository
        .findAllByCriteria(currentLevel, years, certifications, projects, periodLevel)
        .stream()
        .map(employeeMapper::toQueryDTO)
        .toList();
  }

  @Override
  public EmployeeQueryDTO findById(UUID id) {
    return employeeRepository
        .findById(id)
        .map(employeeMapper::toQueryDTO)
        .orElse(null);
  }

  @Override
  public int updateLevel(UUID id, String level) {
    return employeeRepository
        .updateLevel(id, level);
  }
}
