package pe.marcolopez.apps.epp.ms.criteria.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.criteria.constants.CriteriaConstants;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.criteria.entity.CriteriaEntity;
import pe.marcolopez.apps.epp.ms.criteria.proxy.EmployeeProxyService;
import pe.marcolopez.apps.epp.ms.criteria.repository.CriteriaRepository;
import pe.marcolopez.apps.epp.ms.criteria.service.CriteriaService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CriteriaServiceImpl implements CriteriaService {

  private final EmployeeProxyService employeeProxyService;
  private final CriteriaRepository criteriaRepository;

  @Override
  public List<EmployeeQueryDTO> findEmployeesFromPreviousLevelToNewLevel(String currentLevel) {
    Map<String, Integer> criteriaMap = criteriaRepository
        .findAllByLevel(currentLevel)
        .stream()
        .collect(Collectors.toMap(CriteriaEntity::getType, CriteriaEntity::getExpectedValue));

    return employeeProxyService
        .findByCriteria(currentLevel,
            criteriaMap.getOrDefault(CriteriaConstants.YEARS, 0),
            criteriaMap.getOrDefault(CriteriaConstants.CERTIFICATIONS, 0),
            criteriaMap.getOrDefault(CriteriaConstants.PROJECTS, 0))
        .stream()
        .toList();
  }
}
