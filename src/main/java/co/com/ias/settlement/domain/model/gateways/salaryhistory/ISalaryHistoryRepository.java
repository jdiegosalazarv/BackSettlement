package co.com.ias.settlement.domain.model.gateways.salaryhistory;

import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;

import java.time.LocalDate;
import java.util.List;

public interface ISalaryHistoryRepository {

    SalaryHistory saveSalaryHistory(SalaryHistory salaryHistory);

    List<SalaryHistory> findSalaryHistories();

    List<SalaryHistory> findSalaryHistoryByEmployeeId(String employeeId);

    List<SalaryHistory> findSalaryHistoryByEmployeeIdActualYear(String employeeId, LocalDate lastYear);
}
