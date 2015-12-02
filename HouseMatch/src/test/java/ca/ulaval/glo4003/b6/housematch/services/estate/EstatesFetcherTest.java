package ca.ulaval.glo4003.b6.housematch.services.estate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

public class EstatesFetcherTest {

   private static final int FIRST = 0;

   private static final String SELLER_NAME = "SELLER";

   private static final String ADDRESS = "ADDRESS";

   @Mock
   private List<String> listNames;

   @Mock
   private EstateAssemblerFactory estateAssemblerFactory;

   @Mock
   private EstateRepository estateRepository;

   @Mock
   private Estate estate;

   @Mock
   private Estates estates;

   @Mock
   private EstateAssembler estateAssembler;

   @Mock
   private EstateDto estateDto;

   @Mock
   EstatesProcessor estatesProcessor;

   @InjectMocks
   private EstatesFetcher estateFetcher;

   @Mock
   private List<EstateDto> estatesDto;

   @Before
   public void setup() throws SellerNotFoundException, CouldNotAccessDataException, EstateNotFoundException {
      MockitoAnnotations.initMocks(this);
      configureEstateRepository();
      configureEstateProcessor();
      configureEstateAssembler();
      configureFetchingEstateByAddress();

   }

   private void configureEstateProcessor() {
      when(estatesProcessor.retrieveEstatesBySellerName(estates, SELLER_NAME)).thenReturn(estates);
      when(estatesProcessor.retrieveEstatesSoldLastYear(estates)).thenReturn(estates);
      when(estatesProcessor.retrieveUniqueSellersName(estates)).thenReturn(listNames);
   }

   private void configureFetchingEstateByAddress() throws EstateNotFoundException, CouldNotAccessDataException {
      when(estateRepository.getEstateByAddress(ADDRESS)).thenReturn(estate);
   }

   private void configureEstateAssembler() {
      when(estateAssemblerFactory.createEstateAssembler()).thenReturn(estateAssembler);
      when(estateAssembler.assembleEstateDto(estate)).thenReturn(estateDto);
      when(estateAssembler.assembleEstate(estateDto)).thenReturn(estate);
      when(estateAssembler.assembleEstatesDto(estates)).thenReturn(estatesDto);
   }

   private void configureEstateRepository() throws SellerNotFoundException, CouldNotAccessDataException {
      when(estateRepository.getAllEstates()).thenReturn(estates);
   }

   @Test
   public void givenValidRepositoryWhenFetchEstateBySellerShouldDelegateGettingEstateToRepository()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      verify(estateRepository).getAllEstates();
   }

   @Test
   public void givenValidProcessorWhenFetchEstateBySellerShouldDelegateFilteringToEstateProcessor()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      verify(estatesProcessor).retrieveEstatesBySellerName(estates, SELLER_NAME);
   }

   @Test
   public void givenValidAssemblerFactoryWhenFetchEstateBySellerShouldDelegateCreatingAssembler()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      verify(estateAssemblerFactory).createEstateAssembler();
   }

   @Test
   public void givenValidAssemblerWhenFetchEstateBySellerShouldDelegateAssembling()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      verify(estateAssembler).assembleEstatesDto(estates);
   }

   @Test
   public void whenFetchEstateBySellerShouldReturnListOfEstateDto()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      List<EstateDto> rep = estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      assertEquals(estatesDto, rep);
   }

   @Test
   public void givenValidRepositoryWhenGetEstateByAddressShouldDelegateGettingEstateToRepository()
         throws EstateNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getEstateByAddress(ADDRESS);

      // Then
      verify(estateRepository).getEstateByAddress(ADDRESS);
   }

   @Test
   public void givenValidAssemblerFactoryWhenGetEstateByAddressShouldDelegateCreatingAssembler()
         throws EstateNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getEstateByAddress(ADDRESS);

      // Then
      verify(estateAssemblerFactory).createEstateAssembler();
   }

   @Test
   public void givenValidAssemblerWhenGetEstateByAddressShouldDelegateAssemblingEstate()
         throws EstateNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getEstateByAddress(ADDRESS);

      // Then
      verify(estateAssembler).assembleEstateDto(estate);
   }

   @Test
   public void whenGetEstateByAddressShouldReturnEstateDto()
         throws EstateNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      EstateDto rep = estateFetcher.getEstateByAddress(ADDRESS);

      // Then
      assertEquals(estateDto, rep);
   }

   @Test
   public void givenValidRepositoryWhenGetAllEstatesShouldDelegateGettingEstateToRepository()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getAllEstates();

      // Then
      verify(estateRepository).getAllEstates();
   }

   @Test
   public void givenValidAssemblerFactoryWhenGetAllEstatesShouldDelegateCreatingAssembler()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();

      // Then
      verify(estateAssemblerFactory).createEstateAssembler();
   }

   @Test
   public void givenValidAssemblerWhenGetAllEstatesShouldDelegateAssembling()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();

      // Then
      verify(estateAssembler).assembleEstatesDto(estates);
   }

   @Test
   public void whenGetAllEstatesShouldReturnListOfEstateDto()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      List<EstateDto> rep = estateFetcher.getAllEstates();

      // Then
      assertEquals(estatesDto, rep);
   }

   @Test
   public void givenValidEstatesWhenGetPriceOrderedAscendantEstatesShouldDelegateSortingToEstate()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getAllEstates();
      estateFetcher.getPriceOrderedAscendantEstates();

      // Then
      verify(estates).sortByLowestToHighestPrice();
   }

   @Test
   public void givenValidAssemblerFactoryWhenGetPriceOrderedAscendantEstatesShouldDelegateCreatingAssembler()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();
      estateFetcher.getPriceOrderedAscendantEstates();

      // Then
      verify(estateAssemblerFactory, times(2)).createEstateAssembler();
   }

   @Test
   public void givenValidAssemblerWhenGetPriceOrderedAscendantEstatesShouldDelegateAssembling()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();
      estateFetcher.getPriceOrderedAscendantEstates();

      // Then
      verify(estateAssembler, times(2)).assembleEstatesDto(estates);
   }

   @Test
   public void whenGetPriceOrderedAscendantEstatesShouldReturnListOfEstateDto()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();
      List<EstateDto> rep = estateFetcher.getPriceOrderedAscendantEstates();

      // Then
      assertEquals(estatesDto, rep);
   }

   @Test
   public void givenValidEstatesWhenGetPriceOrderedDescendantEstatesShouldDelegateSortingToEstate()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getAllEstates();
      estateFetcher.getPriceOrderedDescendantEstates();

      // Then
      verify(estates).sortByHighestToLowestPrice();
   }

   @Test
   public void givenValidAssemblerFactoryWhenGetPriceOrderedDescendantEstatesShouldDelegateCreatingAssembler()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();
      estateFetcher.getPriceOrderedDescendantEstates();

      // Then
      verify(estateAssemblerFactory, times(2)).createEstateAssembler();
   }

   @Test
   public void givenValidAssemblerWhenGetPriceOrderedDescendantEstatesShouldDelegateAssembling()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();
      estateFetcher.getPriceOrderedDescendantEstates();

      // Then
      verify(estateAssembler, times(2)).assembleEstatesDto(estates);
   }

   @Test
   public void whenGetPriceOrderedDescendantEstatesShouldReturnListOfEstateDto()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();
      List<EstateDto> rep = estateFetcher.getPriceOrderedDescendantEstates();

      // Then
      assertEquals(estatesDto, rep);
   }

   @Test
   public void givenValidEstatesWhenGetDateOrderedAscendantEstatesShouldDelegateSortingToEstate()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getAllEstates();
      estateFetcher.getDateOrderedAscendantEstates();

      // Then
      verify(estates).sortByOldestToNewestDate();
   }

   @Test
   public void givenValidAssemblerFactoryWhenGetDateOrderedAscendantEstatesShouldDelegateCreatingAssembler()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();
      estateFetcher.getDateOrderedAscendantEstates();

      // Then
      verify(estateAssemblerFactory, times(2)).createEstateAssembler();
   }

   @Test
   public void givenValidAssemblerWhenGetDateOrderedAscendantEstatesShouldDelegateAssembling()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();
      estateFetcher.getDateOrderedAscendantEstates();

      // Then
      verify(estateAssembler, times(2)).assembleEstatesDto(estates);
   }

   @Test
   public void whenGetDateOrderedAscendantEstatesShouldReturnListOfEstateDto()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();
      List<EstateDto> rep = estateFetcher.getDateOrderedAscendantEstates();

      // Then
      assertEquals(estatesDto, rep);
   }

   @Test
   public void givenValidEstatesWhenGetDateOrderedDescendantEstatesShouldDelegateSortingToEstate()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getAllEstates();
      estateFetcher.getDateOrderedDescendantEstates();

      // Then
      verify(estates).sortByNewestToOldestDate();
   }

   @Test
   public void givenValidAssemblerFactoryWhenGetDateOrderedDescendantEstatesShouldDelegateCreatingAssembler()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();
      estateFetcher.getDateOrderedDescendantEstates();

      // Then
      verify(estateAssemblerFactory, times(2)).createEstateAssembler();
   }

   @Test
   public void givenValidAssemblerWhenGetDateOrderedDescendantEstatesShouldDelegateAssembling()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();
      estateFetcher.getDateOrderedDescendantEstates();

      // Then
      verify(estateAssembler, times(2)).assembleEstatesDto(estates);
   }

   @Test
   public void whenGetDateOrderedDescendantEstatesShouldReturnListOfEstateDto()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      // When
      estateFetcher.getAllEstates();
      List<EstateDto> rep = estateFetcher.getDateOrderedDescendantEstates();

      // Then
      assertEquals(estatesDto, rep);
   }

}
