package pe.marcolopez.apps.epp.ms.employee.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.marcolopez.apps.epp.ms.employee.dto.EmployeeQueryDTO;
import pe.marcolopez.apps.epp.ms.employee.mapper.EmployeeMapper;
import pe.marcolopez.apps.epp.ms.employee.repository.EmployeeRepository;
import pe.marcolopez.apps.epp.ms.employee.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeQueryDTO> findAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeMapper::toQueryDTO)
                .toList();
    }

    @Override
    public EmployeeQueryDTO findById(UUID id) {
        return employeeRepository
                .findById(id)
                .map(employeeMapper::toQueryDTO)
                .orElse(null);
    }

    @Override
    public int updateLevel(UUID id, String level) {
        return employeeRepository
            .updateLevel(id, level);
    }
}
