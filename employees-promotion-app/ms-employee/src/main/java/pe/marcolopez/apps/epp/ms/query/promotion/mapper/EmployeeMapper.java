package pe.marcolopez.apps.epp.ms.query.promotion.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.query.promotion.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.query.promotion.entity.EmployeeEntity;

@Mapper(
        componentModel = "spring"
)
public interface EmployeeMapper {

    EmployeeEntity toEntity(EmployeeQueryDTO employeeQueryDTO);

    EmployeeQueryDTO toQueryDTO(EmployeeEntity employeeEntity);
}
