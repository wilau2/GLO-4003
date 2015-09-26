package ca.ulaval.glo4003.b6.housematch.estates.repository.factory;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.persistences.EstateElementAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.estates.repository.XMLEstateRepository;

public class XmlEstateRepositoryFactory implements EstateRepositoryFactory {

   @Override
   public EstateRepository newInstance(EstateAssemblerFactory estateAssemblerFactory,
         EstatePersistenceDtoFactory estatePersistenceDtoFactory) {
      return new XMLEstateRepository(estateAssemblerFactory, estatePersistenceDtoFactory,
            new EstateElementAssemblerFactory());
   }

}