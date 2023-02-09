package co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_state")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeStateDBO {
    @Id
    private Integer id;
    private String stateName;

    public static EmployeeState toDomain(EmployeeStateDBO employeeStateDBO) {
        return new EmployeeState(
                new StateId(employeeStateDBO.getId()),
                new StateName(employeeStateDBO.getStateName())
        );
    }

    public static EmployeeStateDBO fromDomain(EmployeeState employeeState) {
        return new EmployeeStateDBO(
                employeeState.getStateId().getValue(),
                employeeState.getStateName().getValue()
        );
    }
}
