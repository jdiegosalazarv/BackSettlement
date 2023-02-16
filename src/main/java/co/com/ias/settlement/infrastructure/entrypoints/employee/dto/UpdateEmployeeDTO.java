package co.com.ias.settlement.infrastructure.entrypoints.employee.dto;

import co.com.ias.settlement.domain.model.employee.EmployeePosition;
import co.com.ias.settlement.domain.model.employee.IdentificationId;
import co.com.ias.settlement.domain.model.employee.Salary;
import co.com.ias.settlement.domain.model.employee.UpdateEmployDate;
import co.com.ias.settlement.domain.model.updateemployee.UpdateEmployee;
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
public class UpdateEmployeeDTO {
    @NotBlank(message = "La cédula no puede estar vacía")
    @Size(min = 7, max = 15, message = "La cédula debe tener entre 7 y 15 caracteres")
    private String identificationId;

    @Size(min = 10, max = 30, message = "El cargo debe estar entre 10 y 30 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "El cargo no debe contener caracteres especiales")
    private String employeePosition;

    @NotNull(message = "El salario no puede estar vacío")
    @Min(value = 1160000, message = "El salario debe ser mayor a $1'160.000")
    @Max(value = 7000000, message = "El salario debe ser menor a $7'000.000")
    private Double salary;

    @Pattern(regexp = "^\\d{4}\\/(0[1-9]|1[0-9]|2[0-9]|3[0-1])\\/(0[1-9]|1[0-2])$", message = "La fecha debe cumplir con" +
            " el formato yyyy/dd/MM")
    @NotBlank(message = "La fecha no puede estar vacía")
    private String updateEmployeeDate;

    public static UpdateEmployee toDomain(UpdateEmployeeDTO updateEmployeeDTO) {
        return new UpdateEmployee(
                new IdentificationId(updateEmployeeDTO.getIdentificationId()),
                (updateEmployeeDTO.getEmployeePosition() == null) ? null :
                        new EmployeePosition(updateEmployeeDTO.getEmployeePosition()),
                new Salary(updateEmployeeDTO.getSalary()),
                new UpdateEmployDate(LocalDate.parse(updateEmployeeDTO.getUpdateEmployeeDate(), Constants.FORMATTER))
        );
    }

}
