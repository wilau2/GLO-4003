package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.ArrayList;
import java.util.Collection;

import ca.ulaval.glo4003.b6.housematch.estates.Estate;

public class InMemoryEstateRepository implements EstateRepository {

   protected final Collection<Estate> estateList;

   public InMemoryEstateRepository() {
      estateList = new ArrayList<>();
   }

   @Override
   public void saveEstate(Estate estate) {
      estateList.add(estate);
   }

   @Override
   public Collection<Estate> getAllEstate() {
      return estateList;
   }

}
