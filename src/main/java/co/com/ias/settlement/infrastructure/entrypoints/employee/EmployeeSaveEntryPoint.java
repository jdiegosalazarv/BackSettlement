package co.com.ias.settlement.infrastructure.entrypoints.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.usecase.employee.EmployeeSaveUseCase;
import co.com.ias.settlement.domain.usecase.employeestate.EmployeeStateFindByIdUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.employee.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeSaveEntryPoint {
    private final EmployeeSaveUseCase employeeSaveUseCase;
    private final EmployeeStateFindByIdUseCase employeeStateFindByIdUseCase;

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            EmployeeState employeeState = this.employeeStateFindByIdUseCase.findByIdEmployeeState(employeeDTO.getEmployeeState());
            Employee employee = EmployeeDTO.toDomain(employeeDTO, employeeState);
            EmployeeDTO employeeDTO1 = EmployeeDTO.fromDomain(this.employeeSaveUseCase.saveEmployee(employee));
            return ResponseEntity.status(201).body(employeeDTO1);
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}
