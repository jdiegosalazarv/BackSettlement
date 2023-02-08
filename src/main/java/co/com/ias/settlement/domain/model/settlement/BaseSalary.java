package co.com.ias.settlement.domain.model.settlement;

public class BaseSalary {

    private final Double value;

    public BaseSalary(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
