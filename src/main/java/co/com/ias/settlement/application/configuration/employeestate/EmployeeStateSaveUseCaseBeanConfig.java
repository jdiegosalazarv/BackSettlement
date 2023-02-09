package co.com.ias.settlement.application.configuration.employeestate;


import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateSaveRepository;
import co.com.ias.settlement.domain.usecase.employeestate.EmployeeStateSaveUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeStateSaveUseCaseBeanConfig {
    @Bean
    public EmployeeStateSaveUseCase employeeStateSaveUseCase(IEmployeeStateSaveRepository iEmployeeStateSaveRepository) {
        return new EmployeeStateSaveUseCase(iEmployeeStateSaveRepository);
    }
}
