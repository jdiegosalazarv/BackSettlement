package co.com.ias.settlement.domain.usecase.salaryhistory;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;

import java.util.List;

public class SalaryHistoryUseCase {

    private final ISalaryHistoryRepository iSalaryHistoryRepository;
    private final IEmployeeRepository iEmployeeRepository;

    public SalaryHistoryUseCase(ISalaryHistoryRepository iSalaryHistoryRepository, IEmployeeRepository iEmployeeRepository) {
        this.iSalaryHistoryRepository = iSalaryHistoryRepository;
        this.iEmployeeRepository = iEmployeeRepository;
    }

    public SalaryHistory saveSalaryHistory(SalaryHistory salaryHistory, String employeeId) {
        Employee employee = this.iEmployeeRepository.findEmployeeById(employeeId);
        SalaryHistory newSalaryHistory = new SalaryHistory(
                salaryHistory.getNewSalary(),
                salaryHistory.getUpdateSalaryDate(),
                employee
        );
        return this.iSalaryHistoryRepository.saveSalaryHistory(newSalaryHistory);
    }

    public List<SalaryHistory> getSalariesHistory() {
        return this.iSalaryHistoryRepository.findSalaryHistories();
    }

    public List<SalaryHistory> getSalariesHistoryByEmployeeId(String employeeId) {
        return this.iSalaryHistoryRepository.findSalaryHistoryByEmployeeId(employeeId);
    }
}
