package pe.marcolopez.apps.epp.ms.query.promotion.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.query.promotion.dto.PromotionQueryDTO;
import pe.marcolopez.apps.epp.ms.query.promotion.mapper.PromotionMapper;
import pe.marcolopez.apps.epp.ms.query.promotion.repository.PromotionRepository;
import pe.marcolopez.apps.epp.ms.query.promotion.service.PromotionService;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

  private final PromotionRepository promotionRepository;
  private final PromotionMapper promotionMapper;

  @Override
  public Flux<PromotionQueryDTO> findAll() {
    return promotionRepository
        .findAll()
        .map(promotionMapper::toQueryDTO)
        .switchIfEmpty(Flux.empty());
  }
}
