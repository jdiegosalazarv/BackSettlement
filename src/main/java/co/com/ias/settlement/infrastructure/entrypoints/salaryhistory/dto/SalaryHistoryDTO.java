package co.com.ias.settlement.infrastructure.entrypoints.salaryhistory.dto;

import co.com.ias.settlement.domain.model.salaryhistory.NewSalary;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.domain.model.salaryhistory.UpdateSalaryDate;
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
public class SalaryHistoryDTO {
    private double newSalary;
    private LocalDate updateSalaryDate;
    private EmployeeDTO employee;

//    public static SalaryHistory toDomain(SalaryHistoryDTO salaryHistoryDTO) {
//        return new SalaryHistory(
//                new NewSalary(salaryHistoryDTO.getNewSalary()),
//                new UpdateSalaryDate(salaryHistoryDTO.getUpdateSalaryDate()),
//                new Employee(
//                        new IdentificationId(salaryHistoryDTO.getEmployee().getIdentificationId()),
//                        new EmployeeName(salaryHistoryDTO.getEmployee().getEmployeeName()),
//                        new ContractStartDate(salaryHistoryDTO.getEmployee().getContractStartDate()),
//                        new EmployeePosition(salaryHistoryDTO.getEmployee().getEmployeePosition()),
//                        new Salary(salaryHistoryDTO.getEmployee().getSalary()),
//                        new UpdateDate(salaryHistoryDTO.getEmployee().getUpdateDate()),
//                        new EmployeeState(
//                                new StateId(salaryHistoryDTO.getEmployee().getEmployeeState().getId()),
//                                new StateName(salaryHistoryDTO.getEmployee().getEmployeeState().getStateName())
//                        )
//                )
//        );
//    }

    public static SalaryHistory toDomain(SalaryHistoryDTO salaryHistoryDTO) {
        return new SalaryHistory(
                new NewSalary(salaryHistoryDTO.getNewSalary()),
                new UpdateSalaryDate(salaryHistoryDTO.getUpdateSalaryDate()),
                null
        );
    }

    public static SalaryHistoryDTO fromDomain(SalaryHistory salaryHistory) {
        return new SalaryHistoryDTO(
                salaryHistory.getNewSalary().getValue(),
                salaryHistory.getUpdateSalaryDate().getValue(),
                new EmployeeDTO(
                        salaryHistory.getEmployee().getIdentificationId().getValue(),
                        salaryHistory.getEmployee().getName().getValue(),
                        salaryHistory.getEmployee().getContractStartDate().getValue(),
                        salaryHistory.getEmployee().getEmployeePosition().getValue(),
                        salaryHistory.getEmployee().getSalary().getValue(),
                        salaryHistory.getEmployee().getUpdateDate().getValue(),
                        new EmployeeStateDTO(
                                salaryHistory.getEmployee().getEmployeeState().getStateId().getValue(),
                                salaryHistory.getEmployee().getEmployeeState().getStateName().getValue()
                        )
                )
        );
    }
}
