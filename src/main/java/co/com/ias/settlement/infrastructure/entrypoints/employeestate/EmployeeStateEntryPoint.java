package co.com.ias.settlement.infrastructure.entrypoints.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.usecase.employeestate.EmployeeStateUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.employeestate.dto.EmployeeStateDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeeState")
@AllArgsConstructor
public class EmployeeStateEntryPoint {

    private final EmployeeStateUseCase employeeStateSaveUseCase;

    @PostMapping
    public ResponseEntity<?> saveEmployeeState(@RequestBody EmployeeStateDTO employeeStateDTO) {
        EmployeeState employeeState = EmployeeStateDTO.toDomain(employeeStateDTO);
        EmployeeState employeeState1 = this.employeeStateSaveUseCase.saveEmployeeState(employeeState);
        return ResponseEntity.status(201).body(EmployeeStateDTO.fromDomain(employeeState1));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> finByIdEmployeeState(@PathVariable Integer id) {
        EmployeeState employeeState = this.employeeStateSaveUseCase.findByIdEmployeeState(id);
        return ResponseEntity.status(200).body(EmployeeStateDTO.fromDomain(employeeState));
    }
}
