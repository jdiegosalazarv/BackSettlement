package co.com.ias.settlement.domain.model.salarychanges;

import java.time.LocalDate;

public class UpdateSalaryDate {

    private final LocalDate value;

    public UpdateSalaryDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
