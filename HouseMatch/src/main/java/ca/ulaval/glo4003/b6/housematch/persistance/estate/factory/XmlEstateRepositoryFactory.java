package ca.ulaval.glo4003.b6.housematch.persistance.estate.factory;

import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLPersistence.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.persistance.estate.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.estate.XMLEstateRepository;
import ca.ulaval.glo4003.b6.housematch.persistance.estate.converter.EstateElementConverterFactory;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstateRepositoryFactory;

public class XmlEstateRepositoryFactory implements EstateRepositoryFactory {

   @Override
   public EstateRepository newInstance(EstateAssemblerFactory estateAssemblerFactory) {
      return new XMLEstateRepository(estateAssemblerFactory, new EstatePersistenceDtoFactory(),
            new EstateElementConverterFactory(), new XMLFileEditor());
   }

}
