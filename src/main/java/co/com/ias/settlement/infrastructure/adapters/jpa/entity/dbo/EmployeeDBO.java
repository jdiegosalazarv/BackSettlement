package co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDBO {
    @Id
    private String identificationId;

    private String employeeName;

    private LocalDate contractStartDate;

    private String employeePosition;

    private Double salary;

    private LocalDate updateEmployeeDate;

    @ManyToOne
    @JoinColumn(name = "employee_state_id", referencedColumnName = "id")
    private EmployeeStateDBO employeeState;

    public static Employee toDomain(EmployeeDBO employeeDBO) {
        return new Employee(
                new IdentificationId(employeeDBO.getIdentificationId()),
                new EmployeeName(employeeDBO.getEmployeeName()),
                new ContractStartDate(employeeDBO.getContractStartDate()),
                (employeeDBO.getEmployeePosition() == null) ? null :
                        new EmployeePosition(employeeDBO.getEmployeePosition()),
                new Salary(employeeDBO.getSalary()),
                (employeeDBO.getUpdateEmployeeDate() == null) ? null :
                        new UpdateEmployDate(employeeDBO.getUpdateEmployeeDate()),
                new EmployeeState(
                        new StateId(employeeDBO.getEmployeeState().getId()),
                        new StateName(employeeDBO.getEmployeeState().getStateName())
                )
        );
    }

    public static EmployeeDBO fromDomain(Employee employee) {
        return new EmployeeDBO(
                employee.getIdentificationId().getValue(),
                employee.getName().getValue(),
                employee.getContractStartDate().getValue(),
                (employee.getEmployeePosition() == null) ? null : employee.getEmployeePosition().getValue(),
                employee.getSalary().getValue(),
                (employee.getUpdateEmployDate() == null) ? null : employee.getUpdateEmployDate().getValue(),
                new EmployeeStateDBO(
                        employee.getEmployeeState().getStateId().getValue(),
                        employee.getEmployeeState().getStateName().getValue()
                )
        );
    }
}
