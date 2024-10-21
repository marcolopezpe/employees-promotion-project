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

@Slf4j
@Service
@AllArgsConstructor
public class CriteriaServiceImpl implements CriteriaService {

  private final EmployeeProxyService employeeProxyService;
  private final CriteriaRepository criteriaRepository;

  @Override
  public List<EmployeeQueryDTO> findEmployeesFromPreviousLevelToNewLevel(String currentLevel, String newLevel, Integer periodLevel) {
    List<CriteriaEntity> criteriaForNewLevel = criteriaRepository.findAllByLevel(newLevel);

    Integer expectedYears = criteriaForNewLevel.stream()
        .filter(c -> c.getType().equalsIgnoreCase(CriteriaConstants.YEARS))
        .findFirst()
        .map(CriteriaEntity::getExpectedValue)
        .orElse(0);

    Integer expectedCertifications = criteriaForNewLevel.stream()
        .filter(c -> c.getType().equalsIgnoreCase(CriteriaConstants.CERTIFICATIONS))
        .findFirst()
        .map(CriteriaEntity::getExpectedValue)
        .orElse(0);

    Integer expectedProjects = criteriaForNewLevel.stream()
        .filter(c -> c.getType().equalsIgnoreCase(CriteriaConstants.PROJECTS))
        .findFirst()
        .map(CriteriaEntity::getExpectedValue)
        .orElse(0);

    return employeeProxyService
        .findByCriteria(
            currentLevel,
            expectedYears,
            expectedCertifications,
            expectedProjects,
            periodLevel)
        .stream()
        .toList();
  }
}
