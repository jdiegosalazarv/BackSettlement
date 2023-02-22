package co.com.ias.settlement.infrastructure.adapters.employeestate;

import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.infrastructure.adapters.jpa.IEmployeeStateRepositoryAdapter;
import co.com.ias.settlement.infrastructure.adapters.jpa.entity.dbo.EmployeeStateDBO;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class EmployeeStateRepositoryAdapterTest {

    @InjectMocks
    EmployeeStateRepositoryAdapter employeeStateRepositoryAdapter;

    @Autowired
    IEmployeeStateRepositoryAdapter iEmployeeStateRepositoryAdapter;

    EmployeeState employeeState;
    EmployeeStateDBO employeeStateDBO;

    @BeforeAll
    void beforeAll() {
        employeeStateRepositoryAdapter = new EmployeeStateRepositoryAdapter(iEmployeeStateRepositoryAdapter);
    }

    @BeforeEach
    void setUp() {
        employeeStateDBO = new EmployeeStateDBO(
                1,
                "Activo"
        );

        employeeState = EmployeeStateDBO.toDomain(employeeStateDBO);
    }

    @Test
    void saveEmployeeState() {
        EmployeeState employeeStateSaved = this.employeeStateRepositoryAdapter.saveEmployeeState(employeeState);

        Assertions.assertEquals(employeeStateDBO.getId(), employeeStateSaved.getStateId().getValue());
        Assertions.assertEquals(employeeStateDBO.getStateName(), employeeStateSaved.getStateName().getValue());
    }

    @Test
    void findByIdEmployeeState() {
        this.employeeStateRepositoryAdapter.saveEmployeeState(employeeState);
        EmployeeState employeeStateFound = this.employeeStateRepositoryAdapter.findByIdEmployeeState(1);

        Assertions.assertEquals("Activo", employeeStateFound.getStateName().getValue());
    }
}