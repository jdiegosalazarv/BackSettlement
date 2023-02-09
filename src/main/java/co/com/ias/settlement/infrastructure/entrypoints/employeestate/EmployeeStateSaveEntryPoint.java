package co.com.ias.settlement.infrastructure.entrypoints.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.usecase.employeestate.EmployeeStateSaveUseCase;
import co.com.ias.settlement.infrastructure.entrypoints.employeestate.dto.EmployeeStateDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employeeState")
@AllArgsConstructor
public class EmployeeStateSaveEntryPoint {

    private final EmployeeStateSaveUseCase employeeStateSaveUseCase;

    @PostMapping
    public ResponseEntity<?> saveEmployeeState(@RequestBody EmployeeStateDTO employeeStateDTO) {
        EmployeeState employeeState = EmployeeStateDTO.toDomain(employeeStateDTO);
        EmployeeState employeeState1 = this.employeeStateSaveUseCase.saveEmployeeState(employeeState);
        return ResponseEntity.status(201).body(EmployeeStateDTO.fromDomain(employeeState1));
    }
}
