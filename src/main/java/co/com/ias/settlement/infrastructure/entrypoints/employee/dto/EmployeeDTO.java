package co.com.ias.settlement.infrastructure.entrypoints.employee.dto;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {

    private String identificationId;

    private String employeeName;

    private LocalDate contractStartDate;

    private String employeePosition;

    private Double salary;

    private LocalDate updateDate;

    private Integer employeeState;

    public static Employee toDomain(EmployeeDTO employeeDTO, EmployeeState employeeState) {
        return new Employee(
                new IdentificationId(employeeDTO.getIdentificationId()),
                new EmployeeName(employeeDTO.getEmployeeName()),
                new ContractStartDate(employeeDTO.getContractStartDate()),
                new EmployeePosition(employeeDTO.getEmployeePosition()),
                new Salary(employeeDTO.getSalary()),
                new UpdateDate(employeeDTO.getUpdateDate()),
                new EmployeeState(employeeState.getStateId(), employeeState.getStateName())
        );
    }

    public static EmployeeDTO fromDomain(Employee employee) {
        return new EmployeeDTO(
                employee.getIdentificationId().getValue(),
                employee.getName().getValue(),
                employee.getContractStartDate().getValue(),
                employee.getEmployeePosition().getValue(),
                employee.getSalary().getValue(),
                employee.getUpdateDate().getValue(),
                employee.getEmployeeState().getStateId().getValue()
        );
    }
}
