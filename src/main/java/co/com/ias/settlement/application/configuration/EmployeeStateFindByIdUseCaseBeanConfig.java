package co.com.ias.settlement.application.configuration;

import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateFindByIdRepository;
import co.com.ias.settlement.domain.usecase.employeestate.EmployeeStateFindByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeStateFindByIdUseCaseBeanConfig {

    @Bean
    public EmployeeStateFindByIdUseCase employeeStateFindByIdUseCase(IEmployeeStateFindByIdRepository iEmployeeStateFindByIdRepository) {
        return new EmployeeStateFindByIdUseCase(iEmployeeStateFindByIdRepository);
    }
}
