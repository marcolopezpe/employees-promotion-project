package pe.marcolopez.apps.epp.ms.consumer.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.marcolopez.apps.epp.ms.consumer.document.PromotionDocument;

import java.util.UUID;

public interface PromotionRepository extends ReactiveMongoRepository<PromotionDocument, UUID> {
}
