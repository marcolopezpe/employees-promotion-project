package pe.marcolopez.apps.epp.ms.criteria.mapper;

import org.mapstruct.Mapper;
import pe.marcolopez.apps.epp.ms.criteria.dto.CriteriaQueryDTO;
import pe.marcolopez.apps.epp.ms.criteria.entity.CriteriaEntity;

@Mapper(
    componentModel = "spring"
)
public interface CriteriaMapper {

  CriteriaQueryDTO toDTO(CriteriaEntity criteriaEntity);
}
