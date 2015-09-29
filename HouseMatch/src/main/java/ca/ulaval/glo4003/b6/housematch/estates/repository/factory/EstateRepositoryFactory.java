package ca.ulaval.glo4003.b6.housematch.estates.repository.factory;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;

public interface EstateRepositoryFactory {

   EstateRepository newInstance(EstateAssemblerFactory estateAssemblerFactory);

}
