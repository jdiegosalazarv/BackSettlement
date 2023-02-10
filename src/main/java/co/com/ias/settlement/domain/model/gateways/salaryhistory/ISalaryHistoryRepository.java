package co.com.ias.settlement.domain.model.gateways.salaryhistory;

import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;

import java.util.List;

public interface ISalaryHistoryRepository {

    SalaryHistory saveSalaryHistory(SalaryHistory salaryHistory);

    List<SalaryHistory> findSalaryHistoryByEmployeeId(String employeeId);
}