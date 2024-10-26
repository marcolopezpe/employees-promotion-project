package pe.marcolopez.apps.epp.ms.command.promotion.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.LevelHistoryCommandDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionEvaluateEmployeeDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionEvaluateLeaderDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.PromotionQueryDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.kafka.producer.PromotionEventProducer;
import pe.marcolopez.apps.epp.ms.command.promotion.mapper.PromotionMapper;
import pe.marcolopez.apps.epp.ms.command.promotion.proxy.EmployeeProxyService;
import pe.marcolopez.apps.epp.ms.command.promotion.repository.PromotionRepository;
import pe.marcolopez.apps.epp.ms.command.promotion.service.PromotionService;
import pe.marcolopez.apps.epp.ms.command.promotion.util.ConvertUtil;
import pe.marcolopez.apps.epp.ms.command.promotion.util.PromotionUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

  private final PromotionRepository promotionRepository;
  private final PromotionMapper promotionMapper;
  private final PromotionEventProducer promotionEventProducer;
  @Qualifier("pe.marcolopez.apps.epp.ms.command.promotion.proxy.EmployeeProxyService")
  private final EmployeeProxyService employeeProxyService;

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
  public List<PromotionQueryDTO> findByEmployeeId(UUID employeeId) {
    return promotionRepository
        .findByEmployeeId(employeeId)
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
              promotionMapper.toEventEmployee(promotionQueryDTO)
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
          promotionEntity.setDecisionDate(LocalDate.now());
          return promotionMapper.toQueryDTO(
              promotionRepository.save(promotionEntity)
          );
        })
        .map(promotionQueryDTO -> {
          promotionEventProducer.sendPromotionLeaderEvaluate(
              promotionMapper.toEventLeader(promotionQueryDTO)
          );
          if (promotionEvaluateLeaderDTO.status().equalsIgnoreCase(PromotionUtil.APPROVED)) {
            employeeProxyService.add(
                LevelHistoryCommandDTO
                    .builder()
                    .employeeId(promotionQueryDTO.employeeId())
                    .previousLevel(ConvertUtil.previousLevel(promotionQueryDTO.proposedLevel()))
                    .newLevel(promotionQueryDTO.proposedLevel())
                    .changeDate(LocalDate.now())
                    .evaluatedBy(promotionQueryDTO.leaderId())
                    .comments(promotionQueryDTO.leaderComments())
                    .build()
            );
            employeeProxyService.updateLevel(
                promotionQueryDTO.employeeId().toString(),
                promotionQueryDTO.proposedLevel()
            );
          }
          return promotionQueryDTO;
        })
        .orElse(null);
  }
}
