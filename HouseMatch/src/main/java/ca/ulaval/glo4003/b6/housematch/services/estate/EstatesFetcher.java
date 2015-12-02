package ca.ulaval.glo4003.b6.housematch.services.estate;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estates;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstatesProcessor;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class EstatesFetcher {

   private EstateRepository estateRepository;

   private Estates inSessionMemoryEstates;

   private EstatesProcessor estatesProcessor;

   private EstateAssemblerFactory estateAssemblerFactory;

   public EstatesFetcher(EstateAssemblerFactory estateAssemblerFactory, EstateRepository estateRepository,
         EstatesProcessor estatesProcessor) {
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepository = estateRepository;
      this.estatesProcessor = estatesProcessor;
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

   public List<EstateDto> getPriceOrderedAscendantEstates() {

      inSessionMemoryEstates.sortByPriceAscendantSort();

      EstateAssembler createEstateAssembler = estateAssemblerFactory.createEstateAssembler();
      List<EstateDto> estatesDto = createEstateAssembler.assembleEstatesDto(inSessionMemoryEstates);

      return estatesDto;

   }

   public List<EstateDto> getPriceOrderedDescendantEstates() {

      inSessionMemoryEstates.sortByPriceDescendantSort();

      EstateAssembler createEstateAssembler = estateAssemblerFactory.createEstateAssembler();
      List<EstateDto> estatesDto = createEstateAssembler.assembleEstatesDto(inSessionMemoryEstates);

      return estatesDto;

   }

   public List<EstateDto> getDateOrderedAscendantEstates() {

      inSessionMemoryEstates.sortByDateAscendantSort();

      EstateAssembler createEstateAssembler = estateAssemblerFactory.createEstateAssembler();
      List<EstateDto> estatesDto = createEstateAssembler.assembleEstatesDto(inSessionMemoryEstates);

      return estatesDto;

   }

   public List<EstateDto> getDateOrderedDescendantEstates() {

      inSessionMemoryEstates.sortByDateDescendantSort();

      EstateAssembler createEstateAssembler = estateAssemblerFactory.createEstateAssembler();
      List<EstateDto> estatesDto = createEstateAssembler.assembleEstatesDto(inSessionMemoryEstates);

      return estatesDto;

   }

}
