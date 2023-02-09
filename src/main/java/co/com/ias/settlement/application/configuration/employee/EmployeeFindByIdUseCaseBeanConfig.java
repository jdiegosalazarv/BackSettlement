package co.com.ias.settlement.application.configuration.employee;

import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeFindByIdRepository;
import co.com.ias.settlement.domain.usecase.employee.EmployeeFindByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeFindByIdUseCaseBeanConfig {

    @Bean
    public EmployeeFindByIdUseCase employeeFindByIdUseCase(IEmployeeFindByIdRepository iEmployeeFindByIdRepository) {
        return new EmployeeFindByIdUseCase(iEmployeeFindByIdRepository);
    }
}
