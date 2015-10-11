package ca.ulaval.glo4003.b6.housematch.estates.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class EstatesFetcher {

   private EstateRepositoryFactory estateRepositoryFactory;

   private EstateAssemblerFactory estateAssemblerFactory;

   @Autowired
   public EstatesFetcher(EstateAssemblerFactory estateAssemblerFactory,
         EstateRepositoryFactory estateRepositoryFactory) {
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepositoryFactory = estateRepositoryFactory;
   }

   public List<EstateDto> getEstatesBySeller(String sellerName)
         throws SellerNotFoundException, CouldNotAccessDataException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);

      List<Estate> sellerEstates = estateRepository.getEstateFromSeller(sellerName);

      List<EstateDto> sellerEstatesDto = convertEstatesToEstatesDto(sellerEstates);

      return sellerEstatesDto;
   }

   private List<EstateDto> convertEstatesToEstatesDto(List<Estate> sellerEstates) {
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      List<EstateDto> sellerEstatesDto = new ArrayList<EstateDto>();
      for (Estate estate : sellerEstates) {
         EstateDto assembledEstateDto = estateAssembler.assembleEstateDto(estate);
         sellerEstatesDto.add(assembledEstateDto);
      }

      return sellerEstatesDto;
   }

   public EstateDto getEstateByAddress(String address) throws EstateNotFoundException, CouldNotAccessDataException {

      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      Estate estate = estateRepository.getEstateByAddress(address);

      EstateAssembler createEstateAssembler = estateAssemblerFactory.createEstateAssembler();
      EstateDto estateDto = createEstateAssembler.assembleEstateDto(estate);

      return estateDto;
   }
   
   public List<EstateDto> getAllEstates() throws CouldNotAccessDataException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);

      List<Estate> allEstates = estateRepository.getAllEstates();
      List<EstateDto> allEstatesDto = convertEstatesToEstatesDto(allEstates);

      return allEstatesDto;
   }

}
