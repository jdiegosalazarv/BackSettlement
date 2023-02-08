package co.com.ias.settlement.domain.model.settlement;

public class PayrollPayable {

    private final Double value;

    public PayrollPayable(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
