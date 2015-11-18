package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.ArrayList;
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

}
