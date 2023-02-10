package co.com.ias.settlement.infrastructure.entrypoints.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
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
    private final Integer EMPLOYEE_STATE_ID = 1;
    private final String EMPLOYEE_STATE_NAME = "Activo";

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeState employeeState = new EmployeeState(new StateId(this.EMPLOYEE_STATE_ID), new StateName(this.EMPLOYEE_STATE_NAME));
        Employee employee = EmployeeDTO.toDomainForSave(employeeDTO, employeeState);
        EmployeeDTO employeeDTO1 = EmployeeDTO.fromDomainForSave(this.employeeSaveUseCase.saveEmployee(employee));
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
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeState employeeState = new EmployeeState(new StateId(employeeDTO.getEmployeeState().getId()),
                new StateName(employeeDTO.getEmployeeState().getStateName()));
        Employee employee = EmployeeDTO.toDomain(employeeDTO, employeeState);
        EmployeeDTO employeeDTO1 = EmployeeDTO.fromDomain(this.employeeSaveUseCase.updateEmployee(employee));
        return ResponseEntity.status(200).body(employeeDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        this.employeeSaveUseCase.deleteEmployee(id);
        return ResponseEntity.status(204).body("Empleado eliminado");
    }
}
