package pe.marcolopez.apps.epp.ms.employee.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.employee.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.employee.entity.EmployeeEntity;

@Mapper(
        componentModel = "spring"
)
public interface EmployeeMapper {

    EmployeeEntity toEntity(EmployeeQueryDTO employeeQueryDTO);

    EmployeeQueryDTO toQueryDTO(EmployeeEntity employeeEntity);
}
