package co.com.ias.settlement.domain.model.employee;

import java.time.LocalDate;

public class ContractStartDate {

    private final LocalDate value;

    public ContractStartDate(LocalDate value) {
        this.validateDate(value);
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    public void validateDate(LocalDate date) {
        if (date.isBefore(LocalDate.of(2015, 01, 01))) {
            throw new IllegalArgumentException("La fecha no debe ser inferior al 01 de enero de 2015");
        } else if (date.isAfter(LocalDate.of(2023, 06, 06))) {
            throw new IllegalArgumentException("La fecha no debe ser posterior al 06 de junio de 2023");
        }
    }
}
