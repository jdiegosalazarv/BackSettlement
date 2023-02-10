package co.com.ias.settlement.application.configuration.bd;

import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeStateRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeStateDBO;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class DataLoader {

    private final IEmployeeStateRepositoryAdapter iEmployeeStateRepositoryAdapter;

    @PostConstruct
    private void loadData() {
        EmployeeStateDBO employeeStateDBO = new EmployeeStateDBO(1, "Activo");
        EmployeeStateDBO employeeStateDBO2 = new EmployeeStateDBO(2, "Inactivo");
        iEmployeeStateRepositoryAdapter.save(employeeStateDBO);
        iEmployeeStateRepositoryAdapter.save(employeeStateDBO2);
    }

}
