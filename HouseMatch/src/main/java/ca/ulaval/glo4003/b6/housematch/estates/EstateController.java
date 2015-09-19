package ca.ulaval.glo4003.b6.housematch.estates;

import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;

public class EstateController {

   private EstateFactory estateFactory;

   private EstateRepository estateRepository;

   public EstateController(EstateFactory estateFactory, EstateRepository estateRepository) {
      this.estateFactory = estateFactory;
      this.estateRepository = estateRepository;
   }

   public EstateController() {
   }

   public void addEstate(EstateDto estateDto) {
      estateFactory.createEstate(estateDto);
   }

   public void saveEstate(Estate estate) {
      estateRepository.saveEstate(estate);

   }

}
