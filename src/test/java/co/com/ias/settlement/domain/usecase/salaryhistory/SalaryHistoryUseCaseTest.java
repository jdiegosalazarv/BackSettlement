package co.com.ias.settlement.domain.usecase.salaryhistory;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.salaryhistory.NewSalary;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.domain.model.salaryhistory.UpdateSalaryDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class SalaryHistoryUseCaseTest {

    @InjectMocks
    SalaryHistoryUseCase salaryHistoryUseCase;

    @Mock
    ISalaryHistoryRepository iSalaryHistoryRepository;

    Employee employee;

    SalaryHistory salaryHistory;

    @BeforeEach
    void setUp() {
        employee = new Employee(
                new IdentificationId("1234567"),
                new EmployeeName("Juan"),
                new ContractStartDate(LocalDate.parse("2020-01-01")),
                new EmployeePosition("Desarrollador"),
                new Salary(3000000D),
                new UpdateEmployDate(LocalDate.parse("2022-01-16")),
                new EmployeeState(
                        new StateId(1),
                        new StateName("Activo")
                )
        );

        salaryHistory = new SalaryHistory(
                new NewSalary(3000000D),
                new UpdateSalaryDate(LocalDate.parse("2022-01-16")),
                employee
        );
    }

    @Test
    void getSalariesHistory() {
        List<SalaryHistory> salaryHistories = Arrays.asList(salaryHistory);
        when(this.iSalaryHistoryRepository.findSalaryHistories()).thenReturn(salaryHistories);

        List<SalaryHistory> result = this.salaryHistoryUseCase.getSalariesHistory();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void getSalariesHistoryByEmployeeId() {
        List<SalaryHistory> salaryHistories = Arrays.asList(salaryHistory);
        when(this.iSalaryHistoryRepository.findSalaryHistoryByEmployeeId("1234567")).thenReturn(salaryHistories);

        List<SalaryHistory> result = this.salaryHistoryUseCase.getSalariesHistoryByEmployeeId("1234567");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Desarrollador", result.get(0).getEmployee().getEmployeePosition().getValue());
    }
    
}