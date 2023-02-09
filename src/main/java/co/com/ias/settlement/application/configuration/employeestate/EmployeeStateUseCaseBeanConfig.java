package co.com.ias.settlement.application.configuration.employeestate;


import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateRepository;
import co.com.ias.settlement.domain.usecase.employeestate.EmployeeStateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeStateUseCaseBeanConfig {
    @Bean
    public EmployeeStateUseCase employeeStateSaveUseCase(IEmployeeStateRepository iEmployeeStateSaveRepository) {
        return new EmployeeStateUseCase(iEmployeeStateSaveRepository);
    }
}
