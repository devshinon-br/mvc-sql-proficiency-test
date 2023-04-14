package mvc.sql.proficiencytest.service;

import mvc.sql.proficiencytest.model.ReportDaily;
import mvc.sql.proficiencytest.model.ReportMonthly;
import mvc.sql.proficiencytest.repository.ReportDailyRepository;
import mvc.sql.proficiencytest.repository.ReportMonthlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportDailyRepository reportDailyRepository;

    @Autowired
    private ReportMonthlyRepository reportMonthlyRepository;

    public List<ReportDaily> findTotalValueAndTotalTimeForEachLicensePlate() {
        return reportDailyRepository.findTotalValueAndTotalTimeForEachLicensePlate();
    }

    public List<ReportMonthly> findNumVehiclesAndTotalValueInMonth() {
        return reportMonthlyRepository.findNumVehiclesAndTotalValueInMonth();
    }

}
