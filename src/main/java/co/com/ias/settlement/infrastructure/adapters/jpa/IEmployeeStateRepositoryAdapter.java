package co.com.ias.settlement.infrastructure.adapters.jpa;

import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeStateDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeStateRepositoryAdapter extends JpaRepository<EmployeeStateDBO, Integer> {
}
