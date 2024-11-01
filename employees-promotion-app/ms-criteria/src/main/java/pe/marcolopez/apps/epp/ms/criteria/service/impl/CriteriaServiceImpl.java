package pe.marcolopez.apps.epp.ms.criteria.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.criteria.constants.CriteriaConstants;
import pe.marcolopez.apps.epp.ms.criteria.dto.CriteriaQueryDTO;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeCommandDTO;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.criteria.entity.CriteriaEntity;
import pe.marcolopez.apps.epp.ms.criteria.kafka.producer.CriteriaEventProducer;
import pe.marcolopez.apps.epp.ms.criteria.mapper.CriteriaMapper;
import pe.marcolopez.apps.epp.ms.criteria.mapper.EmployeeMapper;
import pe.marcolopez.apps.epp.ms.criteria.proxy.EmployeeProxyService;
import pe.marcolopez.apps.epp.ms.criteria.repository.CriteriaRepository;
import pe.marcolopez.apps.epp.ms.criteria.service.CriteriaService;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CriteriaServiceImpl implements CriteriaService {

  @Qualifier("pe.marcolopez.apps.epp.ms.criteria.proxy.EmployeeProxyService")
  private final EmployeeProxyService employeeProxyService;
  private final CriteriaRepository criteriaRepository;
  private final CriteriaEventProducer criteriaEventProducer;
  private final EmployeeMapper employeeMapper;
  private final CriteriaMapper criteriaMapper;

  @Override
  public List<EmployeeQueryDTO> findEligibleEmployees(String currentLevel, String newLevel, Integer periodLevel) {
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

  @Override
  public void notifyEligibleEmployee(EmployeeCommandDTO employeeCommandDTO) {
    criteriaEventProducer.sendEvent(
        employeeMapper.toEvent(employeeCommandDTO)
    );
  }

  @Override
  @Cacheable(value = "findCriteriaQueries")
  public List<CriteriaQueryDTO> findCriteriaQueries() {
    return criteriaRepository
        .findAll()
        .stream()
        .map(criteriaMapper::toDTO)
        .toList();
  }

  @Override
  @Cacheable(value = "findCriteriaQueriesByLevel", key = "#level")
  public List<CriteriaQueryDTO> findCriteriaQueriesByLevel(String level) {
    return criteriaRepository
        .findAllByLevel(level)
        .stream()
        .map(criteriaMapper::toDTO)
        .toList();
  }
}
