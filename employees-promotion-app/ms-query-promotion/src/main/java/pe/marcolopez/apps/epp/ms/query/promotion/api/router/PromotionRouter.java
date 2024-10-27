package pe.marcolopez.apps.epp.ms.query.promotion.api.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.marcolopez.apps.epp.ms.query.promotion.api.handler.PromotionHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class PromotionRouter {

  @Bean
  public RouterFunction<ServerResponse> routePromotion(PromotionHandler promotionHandler) {
    return RouterFunctions
        .route(GET("/promotions"), promotionHandler::findAllPromotions);
  }
}
