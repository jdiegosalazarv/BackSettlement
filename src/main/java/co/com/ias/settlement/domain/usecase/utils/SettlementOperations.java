package co.com.ias.settlement.domain.usecase.utils;

import co.com.ias.settlement.domain.model.employee.Employee;
import co.com.ias.settlement.domain.model.settelementinfo.SettlementInfo;
import co.com.ias.settlement.domain.model.settlement.*;

import java.time.LocalDate;
import java.time.Period;

import static java.time.temporal.ChronoUnit.DAYS;

public class SettlementOperations {

    private static final Double MINIMUM_WAGE = 1160000.0;
    private static final Double TRANSPORTATION_ASSISTANCE = 102854.0;

    private static final Double VACATION_DAYS_PER_YEAR = 15.0;

    private static final Double DAYS_OF_YEAR = 360.0;
    private static final Double VACATIONS_PER_DAY = SettlementOperations.VACATION_DAYS_PER_YEAR / SettlementOperations.DAYS_OF_YEAR;

    public static Settlement generateSettlement(Employee employee, SettlementInfo settlementInfo) {
        return new Settlement(
                null,
                employee,
                new TransportationAssistance(SettlementOperations.setTransportationAssistance(employee.getSalary().getValue())),
                new FinalContractDate(settlementInfo.getFinalContractDate().getValue()),
                new WithdrawalReason(settlementInfo.getWithdrawalReason().getValue()),
                new WorkingDays(SettlementOperations.findWorkingDays(employee.getContractStartDate().getValue(),
                        settlementInfo.getFinalContractDate().getValue())),
                new WorkingDaysActualYear(SettlementOperations.findWorkingDaysLastYear(settlementInfo.getFinalContractDate().getValue())),
                new VacationDays(SettlementOperations.findVacationDays(employee.getContractStartDate().getValue(),
                        settlementInfo.getFinalContractDate().getValue())),
                new WorkingDaysLastSemester(SettlementOperations.findWorkingDaysLastSemester(settlementInfo.getFinalContractDate().getValue())),
                new BaseSalary(SettlementOperations.findBaseSalary(employee.getSalary().getValue())),
                new Severance(SettlementOperations.findSeverance(employee.getSalary().getValue(),
                        employee.getContractStartDate().getValue()
                        , settlementInfo.getFinalContractDate().getValue())),
                new Vacations(SettlementOperations.findVacations(employee.getSalary().getValue(),
                        employee.getContractStartDate().getValue(), settlementInfo.getFinalContractDate().getValue())),
                new SeveranceInterest(SettlementOperations.findSeveranceInterests(employee.getSalary().getValue(),
                        employee.getContractStartDate().getValue(), settlementInfo.getFinalContractDate().getValue())),
                new ServiceBonus(SettlementOperations.findServiceBonus(employee.getSalary().getValue(),
                        settlementInfo.getFinalContractDate().getValue())),
                new PayrollPayable(SettlementOperations.findPayrollPayable(employee.getSalary().getValue(),
                        settlementInfo.getFinalContractDate().getValue())),
                new Bonus(SettlementOperations.findBonus(settlementInfo.getWithdrawalReason().getValue(),
                        employee.getSalary().getValue(), employee.getContractStartDate().getValue(),
                        settlementInfo.getFinalContractDate().getValue())),
                new Total(SettlementOperations.findTotal(employee.getSalary().getValue(),
                        employee.getContractStartDate().getValue(), settlementInfo.getFinalContractDate().getValue(),
                        settlementInfo.getWithdrawalReason().getValue()))
        );
    }

    public static Double setTransportationAssistance(Double employeeSalary) {
        if (employeeSalary <= SettlementOperations.MINIMUM_WAGE * 2) {
            return SettlementOperations.TRANSPORTATION_ASSISTANCE;
        }
        return 0.0;
    }

    public static Integer findWorkingDays(LocalDate startDate, LocalDate endDate) {
        return (int) DAYS.between(startDate, endDate) + 1;
    }

    public static Integer findWorkingDaysLastYear(LocalDate endDate) {
        LocalDate firstDayOfYear = endDate.withDayOfYear(1);
        return (int) DAYS.between(firstDayOfYear, endDate) + 1;
    }

    public static Double findVacationDays(LocalDate startDate, LocalDate endDate) {
        Double workingDays = Double.valueOf(SettlementOperations.findWorkingDays(startDate, endDate));
        return workingDays * VACATIONS_PER_DAY;
    }

    public static Integer findWorkingDaysLastSemester(LocalDate endDate) {
        LocalDate firstDayOfSemester;
        if (endDate.getMonthValue() <= 6) {
            firstDayOfSemester = endDate.withMonth(1).withDayOfMonth(1);
            return (int) DAYS.between(firstDayOfSemester, endDate) + 1;
        } else {
            firstDayOfSemester = endDate.withMonth(7).withDayOfMonth(1);
            return (int) DAYS.between(firstDayOfSemester, endDate) + 1;
        }
    }

    public static Double findBaseSalary(Double employeeSalary) {
        Double transportationAssistance = SettlementOperations.setTransportationAssistance(employeeSalary);
        if (transportationAssistance == 0.0) {
            return employeeSalary;
        }
        return employeeSalary + transportationAssistance;
    }

    public static Double findSeverance(Double employeeSalary, LocalDate startDate, LocalDate endDate) {
        Double workingDays = Double.valueOf(findWorkingDays(startDate, endDate));
        return employeeSalary * workingDays / DAYS_OF_YEAR;
    }

    public static Double findVacations(Double employeeSalary, LocalDate startDate, LocalDate endDate) {
        Double workingDays = Double.valueOf(findWorkingDays(startDate, endDate));
        Double basicSalary = findBaseSalary(employeeSalary);
        return basicSalary * workingDays / 720;
    }

    public static Double findSeveranceInterests(Double employeeSalary, LocalDate startDate, LocalDate endDate) {
        Double severance = SettlementOperations.findSeverance(employeeSalary, startDate, endDate);
        Double workingDays = Double.valueOf(SettlementOperations.findWorkingDays(startDate, endDate));
        return severance * workingDays * 0.12 / DAYS_OF_YEAR;
    }

    public static Double findServiceBonus(Double employeeSalary, LocalDate endDate) {
        Double workingDaysLastSemester = Double.valueOf(findWorkingDaysLastSemester(endDate));
        Double baseSalary = findBaseSalary(employeeSalary);
        return workingDaysLastSemester * baseSalary / DAYS_OF_YEAR;
    }

    public static Double findPayrollPayable(Double employeeSalary, LocalDate endDate) {
        Double baseSalary = SettlementOperations.findBaseSalary(employeeSalary);
        Double SalaryPerDay = baseSalary / DAYS_OF_YEAR;
        LocalDate firstDayOfFortnight;
        if (endDate.getDayOfMonth() <= 15) {
            firstDayOfFortnight = endDate.withDayOfMonth(1);
        } else {
            firstDayOfFortnight = endDate.withDayOfMonth(16);
        }

        Double daysBetween = (double) DAYS.between(firstDayOfFortnight, endDate);
        return daysBetween * SalaryPerDay;
    }

    public static Double findBonus(String withdrawalReason, Double employeeSalary, LocalDate startDate, LocalDate endDate) {
        if (withdrawalReason.equals("Injustified")) {
            Double baseSalary = SettlementOperations.findBaseSalary(employeeSalary);
            Double worrkingYears = (double) Period.between(startDate, endDate).getYears();
            Double salaryPerDay = baseSalary / DAYS_OF_YEAR;
            if (worrkingYears > 1) {
                Double aditional = 0.0;
                for (int i = 1; i < worrkingYears; i++) {
                    aditional += salaryPerDay * 20;
                }
                return baseSalary + aditional;
            }
            return baseSalary;
        }
        return 0.0;
    }

    public static Double findTotal(Double employeeSalary, LocalDate startDate, LocalDate endDate, String withdrawalReason) {
        return findSeverance(employeeSalary, startDate, endDate) + findVacations(employeeSalary, startDate, endDate) +
                findSeveranceInterests(employeeSalary, startDate, endDate) + findServiceBonus(employeeSalary, endDate) + findPayrollPayable(employeeSalary,
                endDate) + +findBonus(withdrawalReason, employeeSalary, startDate, endDate);
    }
}
