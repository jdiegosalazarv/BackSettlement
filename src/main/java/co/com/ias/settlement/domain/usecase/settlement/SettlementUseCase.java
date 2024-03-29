package co.com.ias.settlement.domain.usecase.settlement;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.employee.UpdateEmployDate;
import co.com.ias.settlement.domain.model.employeestate.EmployeeState;
import co.com.ias.settlement.domain.model.gateways.employee.IEmployeeRepository;
import co.com.ias.settlement.domain.model.gateways.employeestate.IEmployeeStateRepository;
import co.com.ias.settlement.domain.model.gateways.salaryhistory.ISalaryHistoryRepository;
import co.com.ias.settlement.domain.model.gateways.settlement.ISettlementRepository;
import co.com.ias.settlement.domain.model.salaryhistory.SalaryHistory;
import co.com.ias.settlement.domain.model.settelementinfo.SettlementInfo;
import co.com.ias.settlement.domain.model.settlement.Settlement;
import co.com.ias.settlement.domain.usecase.utils.SettlementOperations;

import java.time.LocalDate;
import java.util.List;

public class SettlementUseCase {

    private static final Integer EMPLOYEE_STATE_INACTIVE = 2;
    private final ISettlementRepository iSettlementRepository;
    private final IEmployeeRepository iEmployeeRepository;
    private final IEmployeeStateRepository iEmployeeStateRepository;
    private final ISalaryHistoryRepository iSalaryHistoryRepository;

    public SettlementUseCase(ISettlementRepository iSettlementRepository, IEmployeeRepository iEmployeeRepository, IEmployeeStateRepository iEmployeeStateRepository, ISalaryHistoryRepository iSalaryHistoryRepository) {
        this.iSettlementRepository = iSettlementRepository;
        this.iEmployeeRepository = iEmployeeRepository;
        this.iEmployeeStateRepository = iEmployeeStateRepository;
        this.iSalaryHistoryRepository = iSalaryHistoryRepository;
    }

    public Settlement saveSettlement(SettlementInfo settlementInfo) {
        String employeeId = settlementInfo.getEmployeeId().getValue();
        Employee employee = this.iEmployeeRepository.findEmployeeById(employeeId);

        if (employee.getEmployeeState().getStateId().getValue() == EMPLOYEE_STATE_INACTIVE) {
            throw new IllegalArgumentException("El empleado con id: " + employeeId + " no está Activo");
        }

        if (employee.getUpdateEmployDate() == null) {
            if (employee.getContractStartDate().getValue().isAfter(settlementInfo.getFinalContractDate().getValue())) {
                throw new IllegalArgumentException("La fecha de finalización de contrato debe ser posterior a la " +
                        "fecha de contratación del empleado");
            }
        } else if (employee.getUpdateEmployDate().getValue().isAfter(settlementInfo.getFinalContractDate().getValue())) {
            throw new IllegalArgumentException("La fecha de finalización de contrato debe ser posterior a la " +
                    "fecha de actualización del empleado");
        }

        Double baseSalary = 0.0;
        if (employee.getUpdateEmployDate() == null || employee.getUpdateEmployDate().getValue().getYear() == settlementInfo.getFinalContractDate().getValue().getYear()) {
            List<SalaryHistory> salariesActualYear =
                    this.iSalaryHistoryRepository.findSalaryHistoryByEmployeeIdActualYear(employee.getIdentificationId().getValue(),
                            (employee.getUpdateEmployDate() == null) ?
                                    employee.getContractStartDate().getValue() :
                                    LocalDate.ofEpochDay(employee.getUpdateEmployDate().getValue().getYear()));
            baseSalary = SettlementOperations.findBaseSalary(salariesActualYear);
        } else {
            baseSalary = employee.getSalary().getValue() + SettlementOperations.setTransportationAssistance(employee.getSalary().getValue());
        }

        EmployeeState employeeState = this.iEmployeeStateRepository.findByIdEmployeeState(EMPLOYEE_STATE_INACTIVE);
        Employee employeeModified = new Employee(
                employee.getIdentificationId(),
                employee.getName(),
                employee.getContractStartDate(),
                (employee.getEmployeePosition() == null) ? null : employee.getEmployeePosition(),
                employee.getSalary(),
                new UpdateEmployDate(settlementInfo.getFinalContractDate().getValue()),
                employeeState
        );

        Employee employeeUpdated = this.iEmployeeRepository.updateEmployee(employeeModified);
        Settlement settlement = SettlementOperations.generateSettlement(employeeUpdated, settlementInfo, baseSalary);
        return this.iSettlementRepository.saveSattlement(settlement);
    }

    public List<Settlement> findAllSettlements() {
        return this.iSettlementRepository.findAllSettlements();
    }
}
