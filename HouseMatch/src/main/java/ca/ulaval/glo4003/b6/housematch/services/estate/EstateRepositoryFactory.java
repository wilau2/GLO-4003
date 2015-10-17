package ca.ulaval.glo4003.b6.housematch.services.estate;

import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;

public interface EstateRepositoryFactory {

   EstateRepository newInstance(EstateAssemblerFactory estateAssemblerFactory);

}
