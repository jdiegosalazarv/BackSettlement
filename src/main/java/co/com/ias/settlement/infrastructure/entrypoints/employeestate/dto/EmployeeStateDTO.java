package co.com.ias.settlement.infrastructure.entrypoints.employeestate.dto;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeStateDTO {
    private Integer id;
    private String stateName;

    public EmployeeStateDTO(Integer id) {
        this.id = id;
    }

    public static EmployeeState toDomain(EmployeeStateDTO employeeStateDTO) {
        return new EmployeeState(
                new StateId(employeeStateDTO.getId()),
                new StateName(employeeStateDTO.getStateName())
        );
    }

    public static EmployeeStateDTO fromDomain(EmployeeState employeeState) {
        return new EmployeeStateDTO(
                employeeState.getStateId().getValue(),
                employeeState.getStateName().getValue()
        );
    }
}
