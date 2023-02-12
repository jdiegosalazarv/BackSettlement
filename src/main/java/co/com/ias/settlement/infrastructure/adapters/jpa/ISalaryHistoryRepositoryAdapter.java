package co.com.ias.settlement.infrastructure.adapters.jpa;

import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.SalaryHistoryDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ISalaryHistoryRepositoryAdapter extends JpaRepository<SalaryHistoryDBO, Integer> {

    List<SalaryHistoryDBO> findByEmployee_IdentificationId(String identificationId);

    List<SalaryHistoryDBO> findByEmployee_IdentificationIdAndUpdateSalaryDateGreaterThanEqual(String identificationId, LocalDate updateSalaryDate);


}
