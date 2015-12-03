package ca.ulaval.glo4003.b6.housematch.domain.estate;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public interface EstateRepository {

   void addEstate(Estate estate) throws CouldNotAccessDataException;

   Estates getAllEstates() throws CouldNotAccessDataException;

   void updateEstate(Estate estate) throws CouldNotAccessDataException;

   Estate getEstateByAddress(String address) throws EstateNotFoundException, CouldNotAccessDataException;

   void updateEstateModifiedDate(String address) throws EstateNotFoundException, CouldNotAccessDataException;

}
