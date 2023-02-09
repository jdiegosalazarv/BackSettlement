package co.com.ias.settlement.infrastructure.entrypoints.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.usecase.employeestate.EmployeeStateFindByIdUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.employeestate.dto.EmployeeStateDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employeeState")
@AllArgsConstructor
public class EmployeeStateFindByIdEntryPoint {

    private final EmployeeStateFindByIdUseCase employeeStateFindByIdUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<?> finByIdEmployeeState(@PathVariable Integer id) {
        try {
            EmployeeState employeeState = this.employeeStateFindByIdUseCase.findByIdEmployeeState(id);
            return ResponseEntity.status(200).body(EmployeeStateDTO.fromDomain(employeeState));
        } catch (NullPointerException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
