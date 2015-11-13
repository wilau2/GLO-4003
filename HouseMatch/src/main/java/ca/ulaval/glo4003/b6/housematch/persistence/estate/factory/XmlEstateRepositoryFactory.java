package ca.ulaval.glo4003.b6.housematch.persistence.estate.factory;

import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence.FileEditor;
import ca.ulaval.glo4003.b6.housematch.persistence.estate.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.estate.XMLEstateRepository;
import ca.ulaval.glo4003.b6.housematch.persistence.estate.converter.EstateElementConverterFactory;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstateRepositoryFactory;

public class XmlEstateRepositoryFactory implements EstateRepositoryFactory {

   @Override
   public EstateRepository newInstance(EstateAssemblerFactory estateAssemblerFactory) {
      return new XMLEstateRepository(estateAssemblerFactory, new EstatePersistenceDtoFactory(),
            new EstateElementConverterFactory(), new FileEditor());
   }

}
