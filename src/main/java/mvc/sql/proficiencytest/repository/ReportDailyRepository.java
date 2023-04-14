package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.ReportDaily;

import java.util.List;

public interface ReportDailyRepository {

    List<ReportDaily> findTotalValueAndTotalTimeForEachLicensePlate();
}
