package co.com.ias.settlement.domain.model.employee;

import java.time.LocalDate;

public class ContractStartDate {

    private final LocalDate value;

    public ContractStartDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
