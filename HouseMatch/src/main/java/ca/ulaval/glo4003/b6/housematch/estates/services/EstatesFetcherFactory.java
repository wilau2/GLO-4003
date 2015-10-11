package ca.ulaval.glo4003.b6.housematch.estates.services;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;

public class EstatesFetcherFactory {

   public EstatesFetcher newInstance(EstateAssemblerFactory estateAssemblerFactory,
         EstateRepositoryFactory estateRepositoryFactory) {

      return new EstatesFetcher(estateAssemblerFactory, estateRepositoryFactory);
   }

}
