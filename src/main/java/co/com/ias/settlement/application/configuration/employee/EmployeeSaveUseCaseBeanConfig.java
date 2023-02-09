package co.com.ias.settlement.application.configuration.employee;

import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeSaveRepository;
import co.com.ias.settlement.domain.usecase.employee.EmployeeSaveUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeSaveUseCaseBeanConfig {

    @Bean
    public EmployeeSaveUseCase employeeSaveUseCase(IEmployeeSaveRepository iEmployeeSaveRepository) {
        return new EmployeeSaveUseCase(iEmployeeSaveRepository);
    }
}
