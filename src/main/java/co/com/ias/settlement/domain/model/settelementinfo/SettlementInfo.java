package co.com.ias.settlement.domain.model.settelementinfo;

import co.com.ias.settlement.domain.model.employee.IdentificationId;
import co.com.ias.settlement.domain.model.settlement.FinalContractDate;
import co.com.ias.settlement.domain.model.settlement.WithdrawalReason;

public class SettlementInfo {

    private final IdentificationId employeeId;
    private final WithdrawalReason withdrawalReason;
    private final FinalContractDate finalContractDate;

    public SettlementInfo(IdentificationId employeeId, WithdrawalReason withdrawalReason, FinalContractDate finalContractDate) {
        this.employeeId = employeeId;
        this.withdrawalReason = withdrawalReason;
        this.finalContractDate = finalContractDate;
    }

    public IdentificationId getEmployeeId() {
        return employeeId;
    }

    public WithdrawalReason getWithdrawalReason() {
        return withdrawalReason;
    }

    public FinalContractDate getFinalContractDate() {
        return finalContractDate;
    }
}
