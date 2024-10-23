package pe.marcolopez.apps.epp.ms.notification.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.notification.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.notification.entity.EmployeeEntity;

@Mapper(
        componentModel = "spring"
)
public interface EmployeeMapper {

    EmployeeEntity toEntity(EmployeeQueryDTO employeeQueryDTO);

    EmployeeQueryDTO toQueryDTO(EmployeeEntity employeeEntity);
}
