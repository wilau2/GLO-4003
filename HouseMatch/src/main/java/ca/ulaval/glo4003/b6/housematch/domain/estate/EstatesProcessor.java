package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.ArrayList;
import java.util.List;

public class EstatesProcessor {

   public List<String> retrieveUniqueSellersName(Estates estates) {
      List<String> uniqueSellersName = new ArrayList<String>();
      for (Estate estate : estates.retreiveListOfEstate()) {
         String sellerName = estate.getSeller();
         if (!uniqueSellersName.contains(sellerName)) {
            uniqueSellersName.add(sellerName);
         }
      }
      return uniqueSellersName;
   }

   public Estates retrieveEstatesSoldLastYear(Estates estates) {
      List<Estate> listEstateSoldLastYear = new ArrayList<Estate>();
      for (Estate estate : estates.retreiveListOfEstate()) {
         if (estate.hasBeenBoughtInLastYear()) {
            listEstateSoldLastYear.add(estate);
         }
      }
      Estates estatesSoldLastYear = new Estates(listEstateSoldLastYear);
      return estatesSoldLastYear;
   }

   public Estates retrieveEstatesBySellerName(Estates estates, String sellerName) {
      List<Estate> listEstateFromSeller = new ArrayList<Estate>();
      for (Estate estate : estates.retreiveListOfEstate()) {
         if (estate.isFromSeller(sellerName)) {
            listEstateFromSeller.add(estate);
         }
      }
      Estates estatesFromSeller = new Estates(listEstateFromSeller);
      return estatesFromSeller;
   }

}
