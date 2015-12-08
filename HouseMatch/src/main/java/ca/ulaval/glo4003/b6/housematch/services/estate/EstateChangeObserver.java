package ca.ulaval.glo4003.b6.housematch.services.estate;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public interface EstateChangeObserver {

   void notify(String address) throws CouldNotAccessDataException, EstateNotFoundException;

}
