package co.com.ias.settlement.application.configuration.employee;

import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateRepository;
import co.com.ias.settlement.domain.usecase.employee.EmployeeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeUseCaseBeanConfig {

    @Bean
    public EmployeeUseCase employeeSaveUseCase(IEmployeeRepository iEmployeeRepository, IEmployeeStateRepository iEmployeeStateRepository) {
        return new EmployeeUseCase(iEmployeeRepository, iEmployeeStateRepository);
    }
}