package co.com.ias.settlement.infrastructure.entrypoints.employee.dto;

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

    private Integer identificationId;

    private String employeeName;

    private LocalDate contractStartDate;

    private String employeePosition;

    private Double salary;

    private LocalDate updateDate;

    private Integer employeeState;
}
