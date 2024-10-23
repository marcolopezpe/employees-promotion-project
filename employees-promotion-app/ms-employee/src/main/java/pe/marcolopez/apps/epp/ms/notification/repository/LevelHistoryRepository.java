package pe.marcolopez.apps.epp.ms.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.marcolopez.apps.epp.ms.notification.entity.LevelHistoryEntity;
import java.util.List;
import java.util.UUID;

public interface LevelHistoryRepository extends JpaRepository<LevelHistoryEntity, UUID> {

  List<LevelHistoryEntity> findAllByEmployeeId(UUID employeeId);
}
