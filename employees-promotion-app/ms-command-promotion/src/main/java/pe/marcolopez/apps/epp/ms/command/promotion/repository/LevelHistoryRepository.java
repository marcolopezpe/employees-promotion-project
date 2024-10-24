package pe.marcolopez.apps.epp.ms.command.promotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LevelHistoryRepository extends JpaRepository<LevelHistoryEntity, UUID> {

  List<LevelHistoryEntity> findAllByEmployeeId(UUID employeeId);
}
