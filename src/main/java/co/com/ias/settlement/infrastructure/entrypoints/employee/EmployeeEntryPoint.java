package co.com.ias.settlement.infrastructure.entrypoints.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.updateemployee.UpdateEmployee;
import co.com.ias.settlement.domain.usecase.employee.EmployeeUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.employee.dto.EmployeeDTO;
import co.com.ias.settlement.infrastructure.entrypoints.employee.dto.UpdateEmployeeDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeEntryPoint {
    private final EmployeeUseCase employeeSaveUseCase;


    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {

        Employee employee = EmployeeDTO.toDomain(employeeDTO);
        Employee employee1 = this.employeeSaveUseCase.saveEmployee(employee);
        EmployeeDTO employeeDTO1 = EmployeeDTO.fromDomain(employee1);
        return ResponseEntity.status(201).body(employeeDTO1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable String id) {
        Employee employee = this.employeeSaveUseCase.findEmployeByid(id);
        return ResponseEntity.status(200).body(EmployeeDTO.fromDomain(employee));
    }

    @RequestMapping
    public ResponseEntity<?> findEmployees() {
        List<Employee> employees = this.employeeSaveUseCase.findEmployees();
        return ResponseEntity.status(200).body(employees.stream().map(EmployeeDTO::fromDomain).collect(Collectors.toList()));
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody @Valid UpdateEmployeeDTO updateEmployeeDTO) {
        UpdateEmployee updateEmployee = UpdateEmployeeDTO.toDomain(updateEmployeeDTO);
        Employee employee1 = this.employeeSaveUseCase.updateEmployee(updateEmployee);
        EmployeeDTO employeeDTO1 = EmployeeDTO.fromDomain(employee1);
        return ResponseEntity.status(200).body(employeeDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        this.employeeSaveUseCase.deleteEmployee(id);
        return ResponseEntity.status(204).body("Empleado eliminado");
    }
}
