package co.com.ias.settlement.infrastructure.adapters.jpa;

import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepositoryAdapter extends JpaRepository<EmployeeDBO, String> {
}
