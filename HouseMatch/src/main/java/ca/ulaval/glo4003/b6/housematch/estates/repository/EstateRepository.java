package ca.ulaval.glo4003.b6.housematch.estates.repository;

import java.util.Collection;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;

public interface EstateRepository {

   Collection<Estate> getAllEstate();

   void addEstate(Estate estate);

   void editEstate(Estate estate);

}
