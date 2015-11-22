package ca.ulaval.glo4003.b6.housematch.services.estate;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.EstateSorter;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.SortingStrategy;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.SortingStrategyFactory;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class EstatesFetcher {

   private EstateRepositoryFactory estateRepositoryFactory;

   private EstateSorter estateSorter;

   private EstateAssemblerFactory estateAssemblerFactory;

   public EstatesFetcher(EstateAssemblerFactory estateAssemblerFactory, EstateRepositoryFactory estateRepositoryFactory,
         EstateSorter estateSorter) {
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepositoryFactory = estateRepositoryFactory;
      this.estateSorter = estateSorter;
   }

   public List<EstateDto> getEstatesBySeller(String sellerName)
         throws SellerNotFoundException, CouldNotAccessDataException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);

      List<Estate> sellerEstates = estateRepository.getEstateFromSeller(sellerName);

      List<EstateDto> sellerEstatesDto = assembleEstatesDto(sellerEstates);

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
      List<Estate> estates = estateRepository.getAllEstates();

      estateSorter.setEstates(estates);

      List<EstateDto> estatesDto = assembleEstatesDto(estates);

      return estatesDto;

   }

   private List<EstateDto> assembleEstatesDto(List<Estate> estates) {
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      List<EstateDto> estatesDto = new ArrayList<EstateDto>();
      for (Estate estate : estates) {
         EstateDto assembledEstateDto = estateAssembler.assembleEstateDto(estate);
         estatesDto.add(assembledEstateDto);
      }

      return estatesDto;
   }

   public List<EstateDto> getOrderedEstates(String string) {
      SortingStrategyFactory sortingStrategyFactory = new SortingStrategyFactory();
      SortingStrategy sortingStrategy = sortingStrategyFactory.getStrategy(string);
      estateSorter.setContext(sortingStrategy);
      return assembleEstatesDto(estateSorter.sortUsingContext());
   }

   

}
