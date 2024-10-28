package pe.marcolopez.apps.epp.ms.query.promotion.service;

import pe.marcolopez.apps.epp.ms.consumer.dto.PromotionQueryDTO;
import reactor.core.publisher.Flux;

public interface PromotionService {

  Flux<PromotionQueryDTO> findAll();
}
