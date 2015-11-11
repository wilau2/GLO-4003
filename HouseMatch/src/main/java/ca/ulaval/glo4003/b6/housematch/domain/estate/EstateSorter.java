package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.Collections;
import java.util.List;

public class EstateSorter implements Sorter {

   private EstateComparator estateComparator;
   
   public EstateSorter(EstateComparator estateComparator) {
      this.estateComparator = estateComparator;
   }
   
   @Override
   public List<Estate> priceSort(List<Estate> estates) {
      Collections.sort(estates, estateComparator);
      return estates;
   }

}
