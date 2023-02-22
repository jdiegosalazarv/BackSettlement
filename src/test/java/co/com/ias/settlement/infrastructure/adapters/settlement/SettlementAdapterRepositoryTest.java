package co.com.ias.settlement.infrastructure.adapters.settlement;

import co.com.ias.settlement.domain.model.settlement.Settlement;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeStateRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.ISettlementRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeStateDBO;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.SettlementDBO;
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
class SettlementAdapterRepositoryTest {

    @InjectMocks
    SettlementAdapterRepository settlementAdapterRepository;

    @Autowired
    ISettlementRepositoryAdapter iSettlementRepositoryAdapter;
    @Autowired
    IEmployeeRepositoryAdapter iEmployeeRepositoryAdapter;
    @Autowired
    IEmployeeStateRepositoryAdapter iEmployeeStateRepositoryAdapter;
    EmployeeStateDBO employeeStateDBO;
    EmployeeDBO employeeDBO;
    SettlementDBO settlementDBO;
    Settlement settlement;

    @BeforeAll
    void beforeAll() {
        settlementAdapterRepository = new SettlementAdapterRepository(iSettlementRepositoryAdapter);
    }

    @BeforeEach
    void setUp() {
        employeeStateDBO = new EmployeeStateDBO(
                1,
                "Activo"
        );

        employeeDBO = new EmployeeDBO("1234567", "Juan", LocalDate.parse("2020-01-01"), "Desarrollador", 3000000D, null,
                employeeStateDBO);

        settlementDBO = new SettlementDBO(1, employeeDBO, 0D, LocalDate.parse("2022-01-01"), "Justified", 730, 1, 30D, 1,
                3000000D, 1700000D, 3000000D, 120D, 2000000D, 10000D, 0D, 9000000D);

        settlement = SettlementDBO.toDomain(settlementDBO);

        this.iEmployeeStateRepositoryAdapter.save(employeeStateDBO);
        this.iEmployeeRepositoryAdapter.save(employeeDBO);
    }

    @Test
    void saveSattlement() {
        Settlement settlementSaved = this.settlementAdapterRepository.saveSattlement(settlement);

        Assertions.assertEquals(9000000D, settlementSaved.getTotal().getValue());
        Assertions.assertEquals("Justified", settlementSaved.getWithdrawalReason().getValue());
        Assertions.assertEquals(2000000D, settlementSaved.getServiceBonus().getValue());
    }

    @Test
    void findAllSettlements() {
        this.iSettlementRepositoryAdapter.save(settlementDBO);

        List<Settlement> settlements = this.settlementAdapterRepository.findAllSettlements();

        Assertions.assertNotNull(settlements);
        Assertions.assertEquals("1234567", settlements.get(0).getEmployee().getIdentificationId().getValue());
    }
}