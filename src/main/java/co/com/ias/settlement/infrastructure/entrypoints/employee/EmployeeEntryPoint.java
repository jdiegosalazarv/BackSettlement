package co.com.ias.settlement.infrastructure.entrypoints.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.usecase.employee.EmployeeUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.employee.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeEntryPoint {
    private final EmployeeUseCase employeeSaveUseCase;

//    private final EmployeeStateUseCase employeeStateUseCase;

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
//            EmployeeState employeeState =
//                    this.employeeStateUseCase.findByIdEmployeeState(1);
            Employee employee = EmployeeDTO.toDomain(employeeDTO);
            EmployeeDTO employeeDTO1 = EmployeeDTO.fromDomain(this.employeeSaveUseCase.saveEmployee(employee));
            return ResponseEntity.status(201).body(employeeDTO1);
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable String id) {
        Employee employee = this.employeeSaveUseCase.findEmployeByid(id);
        return ResponseEntity.status(200).body(EmployeeDTO.fromDomain(employee));
    }

    @RequestMapping
    public List<EmployeeDTO> findEmployees() {
        List<Employee> employees = this.employeeSaveUseCase.findEmployees();
        return employees.stream().map(EmployeeDTO::fromDomain).collect(Collectors.toList());
    }
}
