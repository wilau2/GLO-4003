package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;

public interface EstateRepository {

   void addEstate(Estate estate);

   void editEstate(Estate estate);

   List<Estate> getAllEstates();

   Estate getEstate(String estateAddress);

}
