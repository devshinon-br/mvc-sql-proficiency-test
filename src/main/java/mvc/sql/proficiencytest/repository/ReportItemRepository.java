package mvc.sql.proficiencytest.repository;

import mvc.sql.proficiencytest.model.ReportItem;

import java.util.List;

public interface ReportItemRepository {

    List<ReportItem> findTotalValueAndTotalTimeForEachLicensePlate();
}
