package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.ReportMonthly;

import java.util.List;

public interface ReportMonthlyRepository {

    List<ReportMonthly> findNumVehiclesAndTotalValueInMonth();
}
