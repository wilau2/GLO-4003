package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.Collections;
import java.util.List;

public class EstateSorter implements Sorter {
   
   private List<Estate> estates;
   
   public EstateSorter() {}

   public void setEstates(List<Estate> estates) {
      this.estates = estates;
   }

   public List<Estate> getPriceDescendantSort() {
      Collections.sort(estates, Estate.EstatePriceDescendantComparator);
      return estates;
   }
   
   public List<Estate> getPriceAscendantSort() {
      Collections.sort(estates, Estate.EstatePriceAscendantComparator);
      return estates;
   }
   
   public List<Estate> getDateDescendantSort() {
      Collections.sort(estates, Estate.EstateDateDescendantComparator);
      return estates;
   }
   
   public List<Estate> getDateAscendantSort() {
      Collections.sort(estates, Estate.EstateDateAscendantComparator);
      return estates;
   }
}
