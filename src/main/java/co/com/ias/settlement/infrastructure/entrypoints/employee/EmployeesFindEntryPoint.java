package co.com.ias.settlement.infrastructure.entrypoints.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.usecase.employee.EmployeesFindUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.employee.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeesFindEntryPoint {

    private final EmployeesFindUseCase employeesFindUseCase;

    @RequestMapping
    public List<EmployeeDTO> findEmployees() {
        List<Employee> employees = this.employeesFindUseCase.findEmployees();
        return employees.stream().map(EmployeeDTO::fromDomain).collect(Collectors.toList());
    }
}
