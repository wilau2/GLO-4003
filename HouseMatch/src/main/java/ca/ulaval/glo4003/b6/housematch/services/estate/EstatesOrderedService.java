package ca.ulaval.glo4003.b6.housematch.services.estate;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateComparator;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstatePriceComparator;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateSorter;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Sorter;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class EstatesOrderedService {
   
   private EstateRepositoryFactory estateRepositoryFactory;
   private EstateAssemblerFactory estateAssemblerFactory;
   private Sorter estateSorter;


   public EstatesOrderedService(EstateRepositoryFactory estateRepositoryFactory, EstateAssemblerFactory estateAssemblerFactory, 
          Sorter estateSorter){
      this.estateRepositoryFactory = estateRepositoryFactory;
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateSorter = estateSorter;
   }
   
   public List<EstateDto> getPriceOrderedEstates() throws CouldNotAccessDataException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      List<Estate> estates = estateRepository.getAllEstates();
      
      estates = estateSorter.priceSort(estates);
      
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

}
