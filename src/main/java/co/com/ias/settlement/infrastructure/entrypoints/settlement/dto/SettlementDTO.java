package co.com.ias.settlement.infrastructure.entrypoints.settlement.dto;

import co.com.ias.settlement.domain.model.settlement.Settlement;
import co.com.ias.settlement.infrastructure.entrypoints.employee.dto.EmployeeDTO;
import co.com.ias.settlement.infrastructure.entrypoints.employeestate.dto.EmployeeStateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SettlementDTO {
    private Integer id;

    private EmployeeDTO employeeDTO;

    private Double transportationAssistance;

    private LocalDate finalContractDate;

    private String withdrawalReason;

    private Integer workingDays;

    private Integer workingDaysActualYear;

    private Double vacationDays;

    private Integer workingDaysLastSemester;

    private Double baseSalary;

    private Double severance;

    private Double vacations;

    private Double severanceInterest;

    private Double serviceBonus;

    private Double payrollPayable;

    private Double bonus;

    private Double total;

    public static SettlementDTO fromDomain(Settlement settlement) {
        return new SettlementDTO(
                settlement.getId().getValue(),
                new EmployeeDTO(
                        settlement.getEmployee().getIdentificationId().getValue(),
                        settlement.getEmployee().getName().getValue(),
                        settlement.getEmployee().getContractStartDate().getValue(),
                        settlement.getEmployee().getEmployeePosition().getValue(),
                        settlement.getEmployee().getSalary().getValue(),
                        (settlement.getEmployee().getUpdateEmployDate() == null) ? null :
                                settlement.getEmployee().getUpdateEmployDate().getValue(),
                        new EmployeeStateDTO(
                                settlement.getEmployee().getEmployeeState().getStateId().getValue(),
                                settlement.getEmployee().getEmployeeState().getStateName().getValue()
                        )
                ),
                settlement.getTransportationAssistance().getValue(),
                settlement.getFinalContractDate().getValue(),
                settlement.getWithdrawalReason().getValue(),
                settlement.getWorkingDays().getValue(),
                settlement.getWorkingDaysActualYear().getValue(),
                settlement.getVacationDays().getValue(),
                settlement.getWorkingDaysLastSemester().getValue(),
                settlement.getBaseSalary().getValue(),
                settlement.getSeverance().getValue(),
                settlement.getVacations().getValue(),
                settlement.getSeveranceInterest().getValue(),
                settlement.getServiceBonus().getValue(),
                settlement.getPayrollPayable().getValue(),
                settlement.getBonus().getValue(),
                settlement.getTotal().getValue()
        );
    }
}
