package co.com.ias.settlement.infrastructure.adapters.salaryhistory;

import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.infrastructure.adapters.jpa.ISalaryHistoryRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.SalaryHistoryDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class SalaryHistoryRepositoryAdapter implements ISalaryHistoryRepository {

    private final ISalaryHistoryRepositoryAdapter iSalaryHistoryRepositoryAdapter;

    @Override
    public SalaryHistory saveSalaryHistory(SalaryHistory salaryHistory) {
        SalaryHistoryDBO salaryHistoryDBO = SalaryHistoryDBO.fromDomain(salaryHistory);
        return SalaryHistoryDBO.toDomain(this.iSalaryHistoryRepositoryAdapter.save(salaryHistoryDBO));
    }

    @Override
    public List<SalaryHistory> findSalaryHistories() {
        List<SalaryHistoryDBO> salaryHistoryBD = this.iSalaryHistoryRepositoryAdapter.findAll();
        return salaryHistoryBD.stream().map(SalaryHistoryDBO::toDomain).toList();
    }

    @Override
    public List<SalaryHistory> findSalaryHistoryByEmployeeId(String employeeId) {
        List<SalaryHistoryDBO> salaryHistoryBD =
                this.iSalaryHistoryRepositoryAdapter.findByEmployee_IdentificationId(employeeId);
        return salaryHistoryBD.stream().map(SalaryHistoryDBO::toDomain).toList();
    }

    @Override
    public List<SalaryHistory> findSalaryHistoryByEmployeeIdActualYear(String employeeId, LocalDate actualYear) {
        List<SalaryHistoryDBO> salaries =
                this.iSalaryHistoryRepositoryAdapter.findByEmployee_IdentificationIdAndUpdateSalaryDateGreaterThanEqual(employeeId, actualYear);
        return salaries.stream().map(SalaryHistoryDBO::toDomain).toList();
    }
}
