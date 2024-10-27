package pe.marcolopez.apps.epp.ms.consumer.promotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.marcolopez.apps.epp.ms.consumer.promotion.entity.LevelHistoryEntity;
import java.util.List;
import java.util.UUID;

public interface LevelHistoryRepository extends JpaRepository<LevelHistoryEntity, UUID> {

  List<LevelHistoryEntity> findAllByEmployeeId(UUID employeeId);
}
