package mvc.sql.proficiencytest.service;

import mvc.sql.proficiencytest.model.ReportItem;
import mvc.sql.proficiencytest.repository.ReportItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportItemService {

    @Autowired
    private ReportItemRepository reportItemRepository;

    public List<ReportItem> findTotalValueAndTotalTimeForEachLicensePlate() {
        return reportItemRepository.findTotalValueAndTotalTimeForEachLicensePlate();
    }

}
