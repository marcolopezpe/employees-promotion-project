package pe.marcolopez.apps.epp.ms.command.promotion.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionEvaluateEmployeeDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionEvaluateLeaderDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionQueryDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.kafka.producer.PromotionEventProducer;
import pe.marcolopez.apps.epp.ms.command.promotion.mapper.PromotionMapper;
import pe.marcolopez.apps.epp.ms.command.promotion.repository.PromotionRepository;
import pe.marcolopez.apps.epp.ms.command.promotion.service.PromotionService;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

  private final PromotionRepository promotionRepository;
  private final PromotionMapper promotionMapper;
  private final PromotionEventProducer promotionEventProducer;

  @Override
  public List<PromotionQueryDTO> findAllByLeaderIdAndStatus(UUID leaderId, String status) {
    return promotionRepository
        .findAllByLeaderIdAndStatus(leaderId, status)
        .stream()
        .map(promotionMapper::toQueryDTO)
        .toList();
  }

  @Override
  public List<PromotionQueryDTO> findAllByLeaderId(UUID leaderId) {
    return promotionRepository
        .findAllByLeaderId(leaderId)
        .stream()
        .map(promotionMapper::toQueryDTO)
        .toList();
  }

  @Override
  public PromotionQueryDTO evaluateByEmployee(PromotionEvaluateEmployeeDTO promotionEvaluateEmployeeDTO) {
    return promotionRepository
        .findById(promotionEvaluateEmployeeDTO.promotionId())
        .map(promotionEntity -> {
          promotionMapper.updateFromDTO(promotionEvaluateEmployeeDTO, promotionEntity);
          return promotionMapper.toQueryDTO(
              promotionRepository.save(promotionEntity)
          );
        })
        .map(promotionQueryDTO -> {
          promotionEventProducer.sendPromotionEmployeeEvaluate(
              promotionMapper.toEvent(promotionQueryDTO)
          );
          return promotionQueryDTO;
        })
        .orElse(null);
  }

  @Override
  public PromotionQueryDTO evaluateByLeader(PromotionEvaluateLeaderDTO promotionEvaluateLeaderDTO) {
    return promotionRepository
        .findById(promotionEvaluateLeaderDTO.promotionId())
        .map(promotionEntity -> {
          promotionMapper.updateFromDTO(promotionEvaluateLeaderDTO, promotionEntity);
          return promotionMapper.toQueryDTO(
              promotionRepository.save(promotionEntity)
          );
        })
        .orElse(null);
  }
}
