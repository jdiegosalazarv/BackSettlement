package co.com.ias.settlement.infrastructure.entrypoints.employee.dto;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.infrastructure.entrypoints.employeestate.dto.EmployeeStateDTO;
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

    private LocalDate updateEmployeeDate;

    private EmployeeStateDTO employeeState;

    public static Employee toDomainForSave(EmployeeDTO employeeDTO, EmployeeState employeeState) {
        return new Employee(
                new IdentificationId(employeeDTO.getIdentificationId()),
                new EmployeeName(employeeDTO.getEmployeeName()),
                new ContractStartDate(employeeDTO.getContractStartDate()),
                new EmployeePosition(employeeDTO.getEmployeePosition()),
                new Salary(employeeDTO.getSalary()),
                null,
                new EmployeeState(employeeState.getStateId(), employeeState.getStateName())
        );
    }

    public static EmployeeDTO fromDomainForSave(Employee employee) {
        return new EmployeeDTO(
                employee.getIdentificationId().getValue(),
                employee.getName().getValue(),
                employee.getContractStartDate().getValue(),
                employee.getEmployeePosition().getValue(),
                employee.getSalary().getValue(),
                null,
                new EmployeeStateDTO(
                        employee.getEmployeeState().getStateId().getValue(),
                        employee.getEmployeeState().getStateName().getValue()
                )
        );
    }

    public static EmployeeDTO fromDomain(Employee employee) {
        return new EmployeeDTO(
                employee.getIdentificationId().getValue(),
                employee.getName().getValue(),
                employee.getContractStartDate().getValue(),
                employee.getEmployeePosition().getValue(),
                employee.getSalary().getValue(),
                employee.getUpdateEmployDate().getValue(),
                new EmployeeStateDTO(
                        employee.getEmployeeState().getStateId().getValue(),
                        employee.getEmployeeState().getStateName().getValue()
                )
        );
    }

    public static Employee toDomain(EmployeeDTO employeeDTO, EmployeeState employeeState) {
        return new Employee(
                new IdentificationId(employeeDTO.getIdentificationId()),
                new EmployeeName(employeeDTO.getEmployeeName()),
                new ContractStartDate(employeeDTO.getContractStartDate()),
                new EmployeePosition(employeeDTO.getEmployeePosition()),
                new Salary(employeeDTO.getSalary()),
                new UpdateEmployDate(LocalDate.now()),
                new EmployeeState(employeeState.getStateId(), employeeState.getStateName())
        );
    }

}
