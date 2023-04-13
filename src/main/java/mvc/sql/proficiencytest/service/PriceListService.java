package mvc.sql.proficiencytest.service;

import mvc.sql.proficiencytest.model.PriceList;
import mvc.sql.proficiencytest.repository.PriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PriceListService {

    @Autowired
    private PriceListRepository priceListRepository;

    public PriceList findPriceListById(final UUID id) {
        return priceListRepository.findPriceListById(id);
    }

    public List<PriceList> listPriceList() {
        return priceListRepository.listPriceLists();
    }

    public void createPriceList(final PriceList priceList) {
        priceListRepository.createPriceList(priceList);
    }

    public void updatePriceList(final PriceList priceList) {
        priceListRepository.updatePriceList(priceList);
    }

    public List<PriceList> getPriceListActived() {
        return priceListRepository.getPriceListActived();
    }

    public void checkStatusTrueAndChange(PriceList priceList) {
        if(priceList.isStatus()) {
            List<PriceList> priceLists = priceListRepository.getPriceListActived();
            if(!priceLists.isEmpty()) {
                for (PriceList priceListActived : priceLists) {
                    priceListActived.setStatus(false);
                    priceListRepository.updatePriceList(priceListActived);
                }
            }
        }
    }

    public void deletePriceList(final UUID id) {
        priceListRepository.deletePriceList(id);
    }
}
