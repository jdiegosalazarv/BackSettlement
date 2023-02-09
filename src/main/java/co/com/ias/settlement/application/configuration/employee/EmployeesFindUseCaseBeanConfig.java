package co.com.ias.settlement.application.configuration.employee;

import co.com.ias.settlement.domain.model.gateways.employee.IEmployeesFindRepository;
import co.com.ias.settlement.domain.usecase.employee.EmployeesFindUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeesFindUseCaseBeanConfig {

    @Bean
    public EmployeesFindUseCase employeesFindUseCaseBean(IEmployeesFindRepository iEmployeesFindRepository) {
        return new EmployeesFindUseCase(iEmployeesFindRepository);
    }
}
