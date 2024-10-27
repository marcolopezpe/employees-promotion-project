package pe.marcolopez.apps.epp.ms.query.promotion.api.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.marcolopez.apps.epp.ms.query.promotion.dto.PromotionQueryDTO;
import pe.marcolopez.apps.epp.ms.query.promotion.service.PromotionService;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PromotionHandler {

  private final PromotionService promotionService;

  public Mono<ServerResponse> findAllPromotions(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(promotionService.findAll(), PromotionQueryDTO.class);
  }
}
