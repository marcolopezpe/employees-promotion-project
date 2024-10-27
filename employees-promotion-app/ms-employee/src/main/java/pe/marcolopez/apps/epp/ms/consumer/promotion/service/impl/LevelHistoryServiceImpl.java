package pe.marcolopez.apps.epp.ms.consumer.promotion.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.consumer.promotion.dto.LevelHistoryCommandDTO;
import pe.marcolopez.apps.epp.ms.consumer.promotion.dto.LevelHistoryQueryDTO;
import pe.marcolopez.apps.epp.ms.consumer.promotion.mapper.LevelHistoryMapper;
import pe.marcolopez.apps.epp.ms.consumer.promotion.repository.LevelHistoryRepository;
import pe.marcolopez.apps.epp.ms.consumer.promotion.service.LevelHistoryService;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class LevelHistoryServiceImpl implements LevelHistoryService {

  private final LevelHistoryMapper levelHistoryMapper;
  private final LevelHistoryRepository levelHistoryRepository;

  @Override
  public List<LevelHistoryQueryDTO> findAll() {
    return levelHistoryRepository
        .findAll()
        .stream()
        .map(levelHistoryMapper::toQueryDTO)
        .toList();
  }

  @Override
  public List<LevelHistoryQueryDTO> findAllByEmployeeId(UUID employeeId) {
    return levelHistoryRepository
        .findAllByEmployeeId(employeeId)
        .stream()
        .map(levelHistoryMapper::toQueryDTO)
        .toList();
  }

  @Override
  public LevelHistoryQueryDTO addLevel(LevelHistoryCommandDTO levelHistoryCommandDTO) {
    return levelHistoryMapper
        .toQueryDTO(
            levelHistoryRepository.save(
                levelHistoryMapper.toEntity(levelHistoryCommandDTO))
        );

  }
}
