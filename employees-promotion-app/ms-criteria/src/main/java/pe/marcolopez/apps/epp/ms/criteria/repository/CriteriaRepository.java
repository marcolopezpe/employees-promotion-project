package pe.marcolopez.apps.epp.ms.criteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.marcolopez.apps.epp.ms.criteria.entity.CriteriaEntity;
import java.util.List;
import java.util.UUID;

public interface CriteriaRepository extends JpaRepository<CriteriaEntity, UUID> {

  List<CriteriaEntity> findAllByLevel(String level);
}
