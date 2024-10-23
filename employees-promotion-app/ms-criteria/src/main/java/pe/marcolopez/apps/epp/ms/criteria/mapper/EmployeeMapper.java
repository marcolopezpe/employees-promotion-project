package pe.marcolopez.apps.epp.ms.criteria.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeCommandDTO;
import pe.marcolopez.apps.epp.ms.kafka.event.EmployeeEligibleEvent;

@Mapper(
    componentModel = "spring"
)
public interface EmployeeMapper {

  EmployeeEligibleEvent toEvent(EmployeeCommandDTO employeeCommandDTO);
}
