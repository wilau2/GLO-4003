package ca.ulaval.glo4003.b6.housematch.persistance.factory;

import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers.EstateElementAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLEstateRepository;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstateRepositoryFactory;

public class XmlEstateRepositoryFactory implements EstateRepositoryFactory {

   @Override
   public EstateRepository newInstance(EstateAssemblerFactory estateAssemblerFactory) {
      return new XMLEstateRepository(estateAssemblerFactory, new EstatePersistenceDtoFactory(),
            new EstateElementAssemblerFactory());
   }

}
