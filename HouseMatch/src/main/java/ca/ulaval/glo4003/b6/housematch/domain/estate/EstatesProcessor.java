package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EstatesProcessor {

   public List<String> retrieveUniqueSellersName(List<Estate> estates) {
      List<String> uniqueSellersName = new ArrayList<String>();
      for (Estate estate : estates) {
         String sellerName = estate.getSeller();

         if (!uniqueSellersName.contains(sellerName)) {
            uniqueSellersName.add(sellerName);
         }
      }
      return uniqueSellersName;
   }

   public List<Estate> retrieveEstatesSoldLastYear(List<Estate> estates) {
      List<Estate> estatesSoldLastYear = new ArrayList<Estate>();
      for (Estate estate : estates) {
         if (estate.hasBeenBoughtInLastYear()) {
            estatesSoldLastYear.add(estate);
         }
      }
      return estatesSoldLastYear;
   }
   
   public HashMap<String, Integer> retrieveNumberEstatesInEachType(List<Estate> estates) {
      HashMap<String, Integer> numberEstatesInEachType = new HashMap<String, Integer>();
      for (Estate estate : estates) {
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
