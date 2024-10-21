package pe.marcolopez.apps.epp.ms.command.promotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.marcolopez.apps.epp.ms.command.promotion.entity.PromotionEntity;
import java.util.UUID;

public interface PromotionRepository extends JpaRepository<PromotionEntity, UUID> {
}
