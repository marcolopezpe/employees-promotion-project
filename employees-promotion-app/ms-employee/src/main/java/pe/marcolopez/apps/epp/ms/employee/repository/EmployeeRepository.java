package pe.marcolopez.apps.epp.ms.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.marcolopez.apps.epp.ms.employee.entity.EmployeeEntity;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

    @Modifying
    @Query("update EmployeeEntity ee set ee.currentLevel=:level where ee.id=:id")
    int updateLevel(@Param("id") UUID id, @Param("level") String level);
}
