package pe.marcolopez.apps.epp.ms.consumer.promotion.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.consumer.promotion.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.consumer.promotion.entity.EmployeeEntity;

@Mapper(
        componentModel = "spring"
)
public interface EmployeeMapper {

    EmployeeEntity toEntity(EmployeeQueryDTO employeeQueryDTO);

    EmployeeQueryDTO toQueryDTO(EmployeeEntity employeeEntity);
}
