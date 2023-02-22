package co.com.ias.settlement.infrastructure.adapters.salaryhistory;

import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeStateRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.ISalaryHistoryRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeStateDBO;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.SalaryHistoryDBO;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class SalaryHistoryRepositoryAdapterTest {

    @InjectMocks
    SalaryHistoryRepositoryAdapter salaryHistoryRepositoryAdapter;
    @Autowired
    ISalaryHistoryRepositoryAdapter iSalaryHistoryRepositoryAdapter;
    @Autowired
    IEmployeeRepositoryAdapter iEmployeeRepositoryAdapter;
    @Autowired
    IEmployeeStateRepositoryAdapter iEmployeeStateRepositoryAdapter;
    EmployeeStateDBO employeeStateDBO;
    EmployeeDBO employeeDBO;

    SalaryHistoryDBO salaryHistoryDBO;

    SalaryHistory salaryHistory;

    @BeforeAll
    void beforeAll() {
        salaryHistoryRepositoryAdapter = new SalaryHistoryRepositoryAdapter(iSalaryHistoryRepositoryAdapter);
    }

    @BeforeEach
    void setUp() {
        employeeStateDBO = new EmployeeStateDBO(
                1,
                "Activo"
        );

        employeeDBO = new EmployeeDBO("1234567", "Juan", LocalDate.parse("2020-01-01"), "Desarrollador", 2000000D, null,
                employeeStateDBO);

        salaryHistoryDBO = new SalaryHistoryDBO(1, employeeDBO.getSalary(), employeeDBO.getContractStartDate(),
                employeeDBO);

        salaryHistory = SalaryHistoryDBO.toDomain(salaryHistoryDBO);

        this.iEmployeeStateRepositoryAdapter.save(employeeStateDBO);
        this.iEmployeeRepositoryAdapter.save(employeeDBO);
    }

    @Test
    void saveSalaryHistory() {
        SalaryHistory salaryHistorySaved = this.salaryHistoryRepositoryAdapter.saveSalaryHistory(salaryHistory);

        Assertions.assertEquals("2020-01-01", salaryHistorySaved.getUpdateSalaryDate().getValue().toString());
    }

    @Test
    void findSalaryHistories() {
        this.iSalaryHistoryRepositoryAdapter.save(salaryHistoryDBO);

        List<SalaryHistory> salaryHistoryList = this.salaryHistoryRepositoryAdapter.findSalaryHistories();

        Assertions.assertEquals(1, salaryHistoryList.size());
        Assertions.assertEquals(1, salaryHistoryList.get(0).getEmployee().getEmployeeState().getStateId().getValue());
    }

    @Test
    void findSalaryHistoryByEmployeeId() {
        this.iSalaryHistoryRepositoryAdapter.save(salaryHistoryDBO);

        List<SalaryHistory> salaryHistoryFound = this.salaryHistoryRepositoryAdapter.findSalaryHistoryByEmployeeId("1234567");

        Assertions.assertEquals(1, salaryHistoryFound.size());
        Assertions.assertEquals("Juan",
                salaryHistoryFound.get(0).getEmployee().getName().getValue());
    }

    @Test
    void findSalaryHistoryByEmployeeIdActualYear() {
        this.iSalaryHistoryRepositoryAdapter.save(salaryHistoryDBO);

        List<SalaryHistory> salaryHistoryFound =
                this.salaryHistoryRepositoryAdapter.findSalaryHistoryByEmployeeIdActualYear("1234567", LocalDate.parse("2020-01-01"));

        Assertions.assertNotNull(salaryHistoryFound);
    }
}