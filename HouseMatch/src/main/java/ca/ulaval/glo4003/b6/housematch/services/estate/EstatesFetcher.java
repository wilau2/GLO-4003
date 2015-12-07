package ca.ulaval.glo4003.b6.housematch.services.estate;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estates;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstatesProcessor;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.EstateFilter;
import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.InconsistentFilterParamaterException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.EstatesSortingStrategy;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.WrongFilterTypeException;

public class EstatesFetcher {

   private EstateRepository estateRepository;

   private Estates inSessionMemoryEstates;

   private EstatesProcessor estatesProcessor;

   private EstateAssemblerFactory estateAssemblerFactory;

   private EstateFilterFactory estateFilterFactory;

   private SortingStrategyFactory sortingStrategyFactory;

   public EstatesFetcher(EstateAssemblerFactory estateAssemblerFactory, EstateRepository estateRepository,
         EstatesProcessor estatesProcessor, SortingStrategyFactory sortingStrategyFactory,
         EstateFilterFactory estateFilterFactory) {
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepository = estateRepository;
      this.estatesProcessor = estatesProcessor;
      this.sortingStrategyFactory = sortingStrategyFactory;
      this.estateFilterFactory = estateFilterFactory;
   }

   public Estates getInSessionMemoryEstates() {
      return inSessionMemoryEstates;

   }

   public List<EstateDto> getEstatesBySeller(String sellerName)
         throws SellerNotFoundException, CouldNotAccessDataException {

      Estates estates = estateRepository.getAllEstates();
      Estates sellerEstates = estatesProcessor.retrieveEstatesBySellerName(estates, sellerName);

      EstateAssembler createEstateAssembler = estateAssemblerFactory.createEstateAssembler();
      List<EstateDto> sellerEstatesDto = createEstateAssembler.assembleEstatesDto(sellerEstates);

      return sellerEstatesDto;

   }

   public EstateDto getEstateByAddress(String address) throws EstateNotFoundException, CouldNotAccessDataException {

      Estate estate = estateRepository.getEstateByAddress(address);

      EstateAssembler createEstateAssembler = estateAssemblerFactory.createEstateAssembler();
      EstateDto estateDto = createEstateAssembler.assembleEstateDto(estate);

      return estateDto;

   }

   public List<EstateDto> getAllEstates() throws CouldNotAccessDataException {

      Estates estates = estateRepository.getAllEstates();

      inSessionMemoryEstates = estates;

      EstateAssembler createEstateAssembler = estateAssemblerFactory.createEstateAssembler();
      List<EstateDto> estatesDto = createEstateAssembler.assembleEstatesDto(estates);

      return estatesDto;

   }

   public List<EstateDto> getSortedEstates(String strategy, boolean descending) {
      EstatesSortingStrategy sortingStrategy = sortingStrategyFactory.getStrategy(strategy);

      inSessionMemoryEstates.sortByStrategy(sortingStrategy);
      if (descending) {
         inSessionMemoryEstates.reverseShownEstates();
      }

      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      return estateAssembler.assembleEstatesDto(inSessionMemoryEstates);
   }

   public List<EstateDto> filter(String price, int minPrice, int maxPrice)
         throws WrongFilterTypeException, InconsistentFilterParamaterException {
      EstateFilter estateFilter = estateFilterFactory.getFilter(price);

      inSessionMemoryEstates.filterEstates(estateFilter, minPrice, maxPrice);

      EstateAssembler createEstateAssembler = estateAssemblerFactory.createEstateAssembler();
      return createEstateAssembler.assembleEstatesDto(inSessionMemoryEstates);

   }

}
