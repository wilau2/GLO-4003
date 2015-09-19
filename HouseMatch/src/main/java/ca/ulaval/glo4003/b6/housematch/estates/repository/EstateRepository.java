package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.Collection;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;

public interface EstateRepository {

   Collection<Estate> getAllEstate();

   void addEstate(Estate estate);

}
