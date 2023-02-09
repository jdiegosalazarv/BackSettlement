package co.com.ias.settlement.domain.model.salarychanges;

public class NewSalary {

    private final Double value;

    public NewSalary(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
