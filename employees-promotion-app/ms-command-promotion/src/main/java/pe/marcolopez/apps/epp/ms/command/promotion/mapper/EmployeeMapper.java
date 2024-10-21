package pe.marcolopez.apps.epp.ms.command.promotion.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.command.promotion.entity.EmployeeEntity;

@Mapper(
        componentModel = "spring"
)
public interface EmployeeMapper {

    EmployeeEntity toEntity(EmployeeQueryDTO employeeQueryDTO);

    EmployeeQueryDTO toQueryDTO(EmployeeEntity employeeEntity);
}
