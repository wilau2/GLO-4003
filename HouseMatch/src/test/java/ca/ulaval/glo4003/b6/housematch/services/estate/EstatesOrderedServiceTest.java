package ca.ulaval.glo4003.b6.housematch.services.estate;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Sorter;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class EstatesOrderedServiceTest { 
   
   private static final int MIN_PRICE = 1;
   private static final int MID_PRICE = 50000;
   private static final int MAX_PRICE = 10000000;
   
   @Mock
   private EstateRepositoryFactory estateRepositoryFactory;

   @Mock
   private EstateAssemblerFactory estateAssemblerFactory;
   
   @Mock
   private EstateAssembler estateAssembler;
   
   @Mock
   private Sorter estateSorter;
   
   @Mock
   private Estate estateMin;
   
   @Mock
   private Estate estateMax;
   
   @Mock
   private Estate estateMid;
   
   @Mock
   private EstateDto estateDtoMin;
   
   @Mock
   private EstateDto estateDtoMid;
   
   @Mock
   private EstateDto estateDtoMax;
   
   @Mock
   private EstateRepository estateRepository;
   
   private List<EstateDto> estatesDto;
   
   private List<Estate> estates;
   
   private List<Estate> estatesSort;
   
   @InjectMocks
   private EstatesOrderedService estatesOrderedService;


   
   @Before
   public void setup() throws CouldNotAccessDataException{
      MockitoAnnotations.initMocks(this);
      estatesOrderedService = new EstatesOrderedService(estateRepositoryFactory, estateAssemblerFactory, estateSorter );
      configureEstateAssembler();
      configureEstateRepository();
      configureEstates();
      configureEstatesDto();
      configureListSortEstates();
      configureSorter();
   }
   
   private void configureSorter() {
      when(estateSorter.priceSort(estates)).thenReturn(estatesSort);
      
   }

   private void configureListSortEstates() {
      estatesSort = new ArrayList<Estate>();
      estatesSort.add(estateMax);
      estatesSort.add(estateMid);
      estatesSort.add(estateMin);
      
   }

   private void configureEstatesDto() {
      when(estateDtoMin.getPrice()).thenReturn(MIN_PRICE);
      when(estateDtoMid.getPrice()).thenReturn(MID_PRICE);
      when(estateDtoMax.getPrice()).thenReturn(MAX_PRICE);
      
   }

   private void configureEstates() {
      when(estateMin.getPrice()).thenReturn(MIN_PRICE);
      when(estateMid.getPrice()).thenReturn(MID_PRICE);
      when(estateMax.getPrice()).thenReturn(MAX_PRICE);
      
   }
   
   private void configureEstateAssembler() {
      when(estateAssemblerFactory.createEstateAssembler()).thenReturn(estateAssembler);
      when(estateAssembler.assembleEstateDto(estateMin)).thenReturn(estateDtoMin);
      when(estateAssembler.assembleEstateDto(estateMid)).thenReturn(estateDtoMid);
      when(estateAssembler.assembleEstateDto(estateMax)).thenReturn(estateDtoMax);
   }

   private void configureEstateRepository() throws CouldNotAccessDataException {
      estates = new ArrayList<Estate>();
      estates.add(estateMid);
      estates.add(estateMax);
      estates.add(estateMin);
      when(estateRepositoryFactory.newInstance(estateAssemblerFactory)).thenReturn(estateRepository);
      when(estateRepository.getAllEstates()).thenReturn(estates);
   }
   
   @Test
   public void whenGetPriceOrderedShouldReturnListEstatesNonNull() throws CouldNotAccessDataException {
      // Given

      // When
      estatesDto = estatesOrderedService.getPriceOrderedEstates();
      
      // Then
      assertTrue(estatesDto != null);
   }
   
   @Test
   public void whenGetPriceOrderedShouldReturnNonEmptyListEstateWhenHaveEstates() throws CouldNotAccessDataException{
   // Given

      // When
      estatesDto = estatesOrderedService.getPriceOrderedEstates();
      
      // Then
      assertTrue(estatesDto.size() != 0);
   }
   
   @Test
   public void whenGetPriceOrderedShouldReturnAscendantPriceListEstates() throws CouldNotAccessDataException {
      // Given

      // When
      estatesDto = estatesOrderedService.getPriceOrderedEstates();
      
      // Then
      assertTrue(estatesDto.get(0).getPrice() == MAX_PRICE);
   }

}
