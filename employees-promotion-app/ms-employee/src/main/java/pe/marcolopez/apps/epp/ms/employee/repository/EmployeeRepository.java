package pe.marcolopez.apps.epp.ms.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.marcolopez.apps.epp.ms.employee.entity.EmployeeEntity;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
}
