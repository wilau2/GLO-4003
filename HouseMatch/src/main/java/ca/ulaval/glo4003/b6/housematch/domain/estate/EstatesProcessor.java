package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.ArrayList;
import java.util.HashMap;
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
      estates.updateEstatesList(listEstateSoldLastYear);
      return estates;
   }

   public Estates retrieveEstatesBySellerName(Estates estates, String sellerName) {
      List<Estate> listEstateFromSeller = new ArrayList<Estate>();
      for (Estate estate : estates.retreiveListOfEstate()) {
         if (estate.isFromSeller(sellerName)) {
            listEstateFromSeller.add(estate);
         }
      }
      estates.updateEstatesList(listEstateFromSeller);
      return estates;
   }
   
   public HashMap<String, Integer> retrieveNumberEstatesInEachType(Estates estates) {
      HashMap<String, Integer> numberEstatesInEachType = new HashMap<String, Integer>();
      for (Estate estate : estates.retreiveListOfEstate()) {
         Integer nbEstate = numberEstatesInEachType.get(estate.getType());
         if (nbEstate != null) {
            numberEstatesInEachType.put(estate.getType(), nbEstate + 1);
         } else {
            numberEstatesInEachType.put(estate.getType(), 1);
         }
      }
      return numberEstatesInEachType;
   }

}
