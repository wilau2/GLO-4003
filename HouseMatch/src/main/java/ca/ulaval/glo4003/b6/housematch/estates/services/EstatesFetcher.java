package ca.ulaval.glo4003.b6.housematch.estates.services;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;

public class EstatesFetcher {

   private EstateRepositoryFactory estateRepositoryFactory;

   private EstatePersistenceDtoFactory estatePersistenceDtoFactory;

   private EstateAssemblerFactory estateAssemblerFactory;

   public EstatesFetcher(EstateAssemblerFactory estateAssemblerFactory,
         EstatePersistenceDtoFactory estatePersistenceDtoFactory, EstateRepositoryFactory estateRepositoryFactory) {
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepositoryFactory = estateRepositoryFactory;
   }

   public List<EstateDto> getEstatesBySeller(String sellerName) {
      estateRepositoryFactory.newInstance(estateAssemblerFactory);

      List<EstateDto> sellerEstatesDto = new ArrayList<EstateDto>();

      EstateDto estateDto = new EstateDto();
      sellerEstatesDto.add(estateDto);
      return sellerEstatesDto;
   }

}
