package co.com.ias.settlement.domain.usecase.settlement;

import co.com.ias.settlement.domain.model.employee.*;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.employeestate.StateId;
import co.com.ias.settlement.domain.model.employeestate.StateName;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateRepository;
import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.gateways.settlement.ISettlementRepository;
import co.com.ias.settlement.domain.model.salaryhistory.NewSalary;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.domain.model.salaryhistory.UpdateSalaryDate;
import co.com.ias.settlement.domain.model.settelementinfo.SettlementInfo;
import co.com.ias.settlement.domain.model.settlement.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class SettlementUseCaseTest {

    @InjectMocks
    SettlementUseCase settlementUseCase;
    @Mock
    ISettlementRepository iSettlementRepository;
    @Mock
    IEmployeeRepository iEmployeeRepository;
    @Mock
    IEmployeeStateRepository iEmployeeStateRepository;
    @Mock
    ISalaryHistoryRepository iSalaryHistoryRepository;

    EmployeeState employeeState;
    Employee employee;
    SalaryHistory salaryHistory;
    SettlementInfo settlementInfo;
    Settlement settlement;

    @BeforeEach
    void setUp() {

        employeeState = new EmployeeState(
                new StateId(2),
                new StateName("Inactivo")
        );

        employee = new Employee(
                new IdentificationId("1234567"),
                new EmployeeName("Juan"),
                new ContractStartDate(LocalDate.parse("2020-01-01")),
                new EmployeePosition("Desarrollador"),
                new Salary(3000000D),
                null,
                new EmployeeState(
                        new StateId(1),
                        new StateName("Activo")
                )
        );

        salaryHistory = new SalaryHistory(
                new NewSalary(3000000D),
                new UpdateSalaryDate(LocalDate.parse("2020-01-01")),
                employee
        );

        settlementInfo = new SettlementInfo(
                new IdentificationId("1234567"),
                new WithdrawalReason("Justified"),
                new FinalContractDate(LocalDate.parse("2023-01-01"))
        );

        settlement = new Settlement(
                new SettlementId(1),
                new Employee(
                        new IdentificationId("1234567"),
                        new EmployeeName("Juan"),
                        new ContractStartDate(LocalDate.parse("2020-01-01")),
                        new EmployeePosition("Desarrollador"),
                        new Salary(3000000D),
                        new UpdateEmployDate(LocalDate.parse("2023-01-01")),
                        employeeState
                ),
                new TransportationAssistance(0D),
                new FinalContractDate(LocalDate.parse("2023-01-01")),
                new WithdrawalReason("Justified"),
                new WorkingDays(366),
                new WorkingDaysActualYear(1),
                new VacationDays(15D),
                new WorkingDaysLastSemester(1),
                new BaseSalary(3000000D),
                new Severance(1700000D),
                new Vacations(3000000D),
                new SeveranceInterest(200000D),
                new ServiceBonus(1700000D),
                new PayrollPayable(150000D),
                new Bonus(0D),
                new Total(6000000D)
        );
    }


    @Test
    void saveSettlement() {
        List<SalaryHistory> salaryHistoryList = Arrays.asList(salaryHistory);
        when(this.iEmployeeRepository.findEmployeeById(any(String.class))).thenReturn(employee);
        when(this.iSalaryHistoryRepository.findSalaryHistoryByEmployeeIdActualYear(any(String.class),
                any(LocalDate.class))).thenReturn(salaryHistoryList);
        when(this.iEmployeeStateRepository.findByIdEmployeeState(any(Integer.class))).thenReturn(employeeState);
        when(this.iEmployeeRepository.updateEmployee(any(Employee.class))).thenReturn(employee);

        Settlement settlementSaved = this.settlementUseCase.saveSettlement(settlementInfo);

        Assertions.assertEquals(6000000D, settlementSaved.getTotal().getValue());
    }

    @Test
    void findAllSettlements() {
        List<Settlement> settlements = Arrays.asList(settlement);
        when(this.iSettlementRepository.findAllSettlements()).thenReturn(settlements);

        List<Settlement> settlementsSaved = this.settlementUseCase.findAllSettlements();

        Assertions.assertNotNull(settlementsSaved);
        Assertions.assertEquals(1, settlementsSaved.size());
    }
}