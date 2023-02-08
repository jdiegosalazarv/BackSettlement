package co.com.ias.settlement.domain.model.settlement;

public class WithdrawalReason {

    private final String value;

    public WithdrawalReason(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
