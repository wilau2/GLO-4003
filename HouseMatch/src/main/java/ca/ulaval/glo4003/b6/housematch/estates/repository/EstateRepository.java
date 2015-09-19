package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.Collection;

import ca.ulaval.glo4003.b6.housematch.estates.Estate;

public interface EstateRepository {

   public void saveEstate(Estate estate);

   public Collection<Estate> getAllEstate();

}
