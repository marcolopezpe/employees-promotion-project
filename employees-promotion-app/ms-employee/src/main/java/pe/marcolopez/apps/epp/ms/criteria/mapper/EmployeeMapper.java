package pe.marcolopez.apps.epp.ms.criteria.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.criteria.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.criteria.entity.EmployeeEntity;

@Mapper(
        componentModel = "spring"
)
public interface EmployeeMapper {

    EmployeeEntity toEntity(EmployeeQueryDTO employeeQueryDTO);

    EmployeeQueryDTO toQueryDTO(EmployeeEntity employeeEntity);
}
