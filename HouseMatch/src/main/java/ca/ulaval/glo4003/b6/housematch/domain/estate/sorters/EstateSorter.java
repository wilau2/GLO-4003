package ca.ulaval.glo4003.b6.housematch.domain.estate.sorters;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public class EstateSorter {
   
   private List<Estate> estates;
   
   private SortingStrategy sortingStrategy;
   
   public EstateSorter() {}

   public void setEstates(List<Estate> estates) {
      this.estates = estates;
   }
   
   public List<Estate> getEstates(){
      return estates;
   }
   
  public void setContext(SortingStrategy sortingStrategy){
     this.sortingStrategy = sortingStrategy;
  }
  
  public  List<Estate> sortUsingContext(){
     return sortingStrategy.sort(estates);
  }
  
}
