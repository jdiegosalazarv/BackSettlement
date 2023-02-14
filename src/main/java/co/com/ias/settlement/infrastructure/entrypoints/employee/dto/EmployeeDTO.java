package co.com.ias.settlement.infrastructure.entrypoints.employee.dto;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import co.com.ias.settlement.infrastructure.entrypoints.employeestate.dto.EmployeeStateDTO;
import co.com.ias.settlement.infrastructure.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {

    @NotBlank(message = "La cédula no puede estar vacía")
    @Size(min = 7, max = 15, message = "La cédula debe tener entre 7 y 15 caracteres")
    private String identificationId;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre debe tener máximo 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "El nombre no debe contener caracteres especiales")
    private String employeeName;

    @NotBlank(message = "La fecha de contrato no puede estar vacía")
    @Pattern(regexp = "^\\d{4}\\/(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(0[1-9]|1[0-2])$", message = "La fecha debe cumplir con" +
            " el formato yyyy/dd/MM")
    private String contractStartDate;

    @Size(min = 10, max = 30, message = "El cargo debe estar entre 10 y 30 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "El cargo no debe contener caracteres especiales")
    private String employeePosition;

    @NotNull(message = "El salario no puede estar vacío")
    @Min(value = 1160000, message = "El salario debe ser mayor a $1'160.000")
    @Max(value = 7000000, message = "El salario debe ser menor a $7'000.000")
    private Double salary;

    @Pattern(regexp = "^\\d{4}\\/(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(0[1-9]|1[0-2])$", message = "La fecha debe cumplir con" +
            " el formato yyyy/dd/MM")
    private String updateEmployeeDate;

    private EmployeeStateDTO employeeState;

    public static Employee toDomain(EmployeeDTO employeeDTO) {
        return new Employee(
                new IdentificationId(employeeDTO.getIdentificationId()),
                new EmployeeName(employeeDTO.getEmployeeName()),
                new ContractStartDate(LocalDate.parse(employeeDTO.getContractStartDate(), Constants.FORMATTER)),
                (employeeDTO.getEmployeePosition() == null) ? null :
                        new EmployeePosition(employeeDTO.getEmployeePosition()),
                new Salary(employeeDTO.getSalary()),
                (employeeDTO.getUpdateEmployeeDate() == null) ? null :
                        new UpdateEmployDate(LocalDate.parse(employeeDTO.getUpdateEmployeeDate(), Constants.FORMATTER)),
                (employeeDTO.getEmployeeState() == null) ? null :
                        new EmployeeState(new StateId(employeeDTO.getEmployeeState().getId()),
                                new StateName(employeeDTO.getEmployeeState().getStateName()))
        );
    }

    public static EmployeeDTO fromDomain(Employee employee) {
        return new EmployeeDTO(
                employee.getIdentificationId().getValue(),
                employee.getName().getValue(),
                employee.getContractStartDate().getValue().format(Constants.FORMATTER),
                (employee.getEmployeePosition() == null) ? null : employee.getEmployeePosition().getValue(),
                employee.getSalary().getValue(),
                (employee.getUpdateEmployDate() == null) ? null : employee.getUpdateEmployDate().getValue().format(Constants.FORMATTER),
                new EmployeeStateDTO(
                        employee.getEmployeeState().getStateId().getValue(),
                        employee.getEmployeeState().getStateName().getValue()
                )
        );
    }

}
