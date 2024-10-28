package pe.marcolopez.apps.epp.ms.query.promotion.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.marcolopez.apps.epp.ms.query.promotion.document.PromotionDocument;

@Repository
public interface PromotionRepository extends ReactiveMongoRepository<PromotionDocument, String> {
}
