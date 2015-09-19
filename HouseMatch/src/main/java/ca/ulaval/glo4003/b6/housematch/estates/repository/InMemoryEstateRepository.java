package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;

public class InMemoryEstateRepository implements EstateRepository {

   private List<Estate> estates = new ArrayList<Estate>();

   @Override
   public Collection<Estate> getAllEstates() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void addEstate(Estate estate) {
      estates.add(estate);
   }

}
