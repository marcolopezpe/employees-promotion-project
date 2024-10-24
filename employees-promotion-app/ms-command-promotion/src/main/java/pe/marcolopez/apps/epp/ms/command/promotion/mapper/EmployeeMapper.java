package pe.marcolopez.apps.epp.ms.command.promotion.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.command.promotion.dto.EmployeeQueryDTO;

@Mapper(
        componentModel = "spring"
)
public interface EmployeeMapper {

    EmployeeEntity toEntity(EmployeeQueryDTO employeeQueryDTO);

    EmployeeQueryDTO toQueryDTO(EmployeeEntity employeeEntity);
}
