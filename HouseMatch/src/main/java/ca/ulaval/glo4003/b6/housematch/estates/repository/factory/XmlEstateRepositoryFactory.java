package ca.ulaval.glo4003.b6.housematch.estates.repository.factory;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.assemblers.EstateElementAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.estates.repository.XMLEstateRepository;

public class XmlEstateRepositoryFactory implements EstateRepositoryFactory {

   @Override
   public EstateRepository newInstance(EstateAssemblerFactory estateAssemblerFactory) {
      return new XMLEstateRepository(estateAssemblerFactory, new EstatePersistenceDtoFactory(),
            new EstateElementAssemblerFactory());
   }

}
