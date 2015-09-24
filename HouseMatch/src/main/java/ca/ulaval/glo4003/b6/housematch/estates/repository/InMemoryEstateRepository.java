package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;

public class InMemoryEstateRepository implements EstateRepository {

   private List<Estate> estates = new ArrayList<Estate>();

   @Override
   public List<Estate> getAllEstates() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void addEstate(Estate estate) {
      estates.add(estate);
   }

   @Override
   public void editEstate(Estate estate) {
      // TODO Auto-generated method stub

   }

}
