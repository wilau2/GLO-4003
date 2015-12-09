package ca.ulaval.glo4003.b6.housematch.services.estate;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class EstateModifiedDateObserver implements EstateChangeObserver {

   private EstateRepository estateRepository;

   public EstateModifiedDateObserver(EstateRepository estateRepository) {
      this.estateRepository = estateRepository;
   }

   @Override
   public void notify(String address) throws CouldNotAccessDataException, EstateNotFoundException {

      Estate estate = estateRepository.getEstateByAddress(address);

      estate.updateModifiedDate();

      estateRepository.updateEstate(estate);

   }

}
