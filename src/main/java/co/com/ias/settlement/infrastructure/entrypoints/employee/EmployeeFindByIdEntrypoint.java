package co.com.ias.settlement.infrastructure.entrypoints.employee;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.usecase.employee.EmployeeFindByIdUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.employee.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeFindByIdEntrypoint {
    private final EmployeeFindByIdUseCase employeeFindByIdUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable String id) {
        Employee employee = this.employeeFindByIdUseCase.findEmployeByid(id);
        return ResponseEntity.status(200).body(EmployeeDTO.fromDomain(employee));
    }
}
