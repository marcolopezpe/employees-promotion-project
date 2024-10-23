package pe.marcolopez.apps.epp.ms.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.marcolopez.apps.epp.ms.notification.entity.EmployeeEntity;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

  @Modifying
  @Query("update EmployeeEntity ee set ee.currentLevel=:level where ee.id=:id")
  int updateLevel(@Param("id") UUID id, @Param("level") String level);

  @Query(value = """
    SELECT ee.*
    FROM tb_employee ee
    WHERE ee.current_level = :currentLevel
      AND EXTRACT(YEAR FROM AGE(CURRENT_DATE, ee.hire_date)) >= :years
      AND ee.certifications >= :certifications
      AND ee.production_projects >= :projects
      AND COALESCE(ee.period_level, 0) != :periodLevel
    ORDER BY ee.firstname ASC
  """, nativeQuery = true)
  List<EmployeeEntity> findAllByCriteria(@Param("currentLevel") String currentLevel,
                                         @Param("years") Integer years,
                                         @Param("certifications") Integer certifications,
                                         @Param("projects") Integer projects,
                                         @Param("periodLevel") Integer periodLevel);
}
