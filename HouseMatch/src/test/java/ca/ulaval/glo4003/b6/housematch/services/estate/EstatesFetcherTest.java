package ca.ulaval.glo4003.b6.housematch.services.estate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.never;
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
import ca.ulaval.glo4003.b6.housematch.domain.estate.SortingStrategyFactory;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.EstateFilter;
import ca.ulaval.glo4003.b6.housematch.domain.estate.filters.InconsistentFilterParamaterException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.sorters.EstatesSortingStrategy;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class EstatesFetcherTest {

   private static final String STRATEGY = "STRATEGY";

   private static final String SELLER_NAME = "SELLER";

   private static final String ADDRESS = "ADDRESS";

   private static final int MIN_PRICE = 1000;

   private static final int MAX_PRICE = 10000;

   private static final String PRICE = "PRICE";

   private static final String WRONG_TYPE = "really_wrong_type";

   private static final String MESSAGE = "bad_parameter";

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
   private EstateFilterFactory estateFilterFactory;

   @Mock
   private EstateFilter estateFilter;

   @Mock
   EstatesProcessor estatesProcessor;

   @InjectMocks
   private EstatesFetcher estateFetcher;

   @Mock
   private List<EstateDto> estatesDto;

   @Mock
   private SortingStrategyFactory estatesSortingFactory;

   @Mock
   private EstatesSortingStrategy estateSortingStrategy;

   @Before
   public void setup() throws SellerNotFoundException, CouldNotAccessDataException, EstateNotFoundException,
         WrongFilterTypeException, InconsistentFilterParamaterException {
      MockitoAnnotations.initMocks(this);

      configureEstateRepository();
      configureEstateProcessor();
      configureEstateAssembler();
      configureFetchingEstateByAddress();

      configureEstateFilter();

      estateFetcher = new EstatesFetcher(estateAssemblerFactory, estateRepository, estatesProcessor,
            estatesSortingFactory, estateFilterFactory);

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

   private void configureEstateFilter() throws WrongFilterTypeException, InconsistentFilterParamaterException {
      when(estateFilterFactory.getFilter(PRICE)).thenReturn(estateFilter);

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

   public void whenFilterWithPriceParameterShouldReturnEstatesNonNull()
         throws WrongFilterTypeException, InconsistentFilterParamaterException, CouldNotAccessDataException {
      // Given
      configureEstatesInMemory();

      // When
      List<EstateDto> estates = estateFetcher.filter(PRICE, MIN_PRICE, MAX_PRICE);

      // Then
      assertTrue(estates != null);
   }

   @Test(expected = WrongFilterTypeException.class)
   public void whenFilterWithWrongTypeParameterShouldThrowWrongFilterTypeException()
         throws WrongFilterTypeException, InconsistentFilterParamaterException, CouldNotAccessDataException {

      // Given
      configureEstatesInMemory();
      when(estateFilterFactory.getFilter(WRONG_TYPE)).thenThrow(new WrongFilterTypeException(MESSAGE));

      // When

      estateFetcher.filter(WRONG_TYPE, MIN_PRICE, MAX_PRICE);
      // Then an Wrong filter type exception is thrown
   }

   @Test
   public void whenFilterWithPriceParameterShouldReturnEstatesWithValue()
         throws WrongFilterTypeException, InconsistentFilterParamaterException, CouldNotAccessDataException {
      // Given
      configureEstatesInMemory();

      // When
      List<EstateDto> returnedEstatesDto = estateFetcher.filter(PRICE, MIN_PRICE, MAX_PRICE);

      // Then
      assertEquals(estatesDto, returnedEstatesDto);
   }

   @Test
   public void whenFilteringEstatesShouldCallEstatesWithGivenFilter()
         throws WrongFilterTypeException, InconsistentFilterParamaterException, CouldNotAccessDataException {
      // Given
      configureEstatesInMemory();

      // When
      estateFetcher.filter(PRICE, MIN_PRICE, MAX_PRICE);

      // Then
      verify(estates, times(1)).filterEstates(estateFilter, MIN_PRICE, MAX_PRICE);
   }

   @Test
   public void whenGettingEstatesOrderedShouldCallEstateStrategyFactory() throws CouldNotAccessDataException {
      // Given no changes
      configureEstatesInMemory();

      // When
      estateFetcher.getSortedEstates(STRATEGY, false);

      // Then
      verify(estatesSortingFactory, times(1)).getStrategy(STRATEGY);
   }

   @Test
   public void whenGettingEstatesOrderedShouldCallEstatesSortWithSortingStrategy() throws CouldNotAccessDataException {
      // Given
      configureEstatesInMemory();
      when(estatesSortingFactory.getStrategy(STRATEGY)).thenReturn(estateSortingStrategy);

      // When
      estateFetcher.getSortedEstates(STRATEGY, false);

      // Then
      verify(estates, times(1)).sortByStrategy(estateSortingStrategy);
   }

   @Test
   public void whenGettingSortedEstatesShouldCallAssemblerFactory() throws CouldNotAccessDataException {
      // Given
      configureEstatesInMemory();
      when(estatesSortingFactory.getStrategy(STRATEGY)).thenReturn(estateSortingStrategy);

      // When
      estateFetcher.getSortedEstates(STRATEGY, false);

      // Then
      verify(estateAssemblerFactory, times(2)).createEstateAssembler();
   }

   @Test
   public void gettingSortedEstatesWhenEstatesNeedToBeOrderedDescendingShouldAskEstatesToRevertItsOrder()
         throws CouldNotAccessDataException {
      // Given
      configureEstatesInMemory();
      when(estatesSortingFactory.getStrategy(STRATEGY)).thenReturn(estateSortingStrategy);

      // When
      estateFetcher.getSortedEstates(STRATEGY, true);

      // Then
      verify(estates, times(1)).reverseShownEstates();
   }

   @Test
   public void gettingSortedEstatesWhenEstatesDoNotNeedToBeDescendingOrderedShouldNotAskEstatesToRevertItsOrder()
         throws CouldNotAccessDataException {
      // Given
      configureEstatesInMemory();
      when(estatesSortingFactory.getStrategy(STRATEGY)).thenReturn(estateSortingStrategy);

      // When
      estateFetcher.getSortedEstates(STRATEGY, false);

      // Then
      verify(estates, never()).reverseShownEstates();
   }

   private void configureEstatesInMemory() throws CouldNotAccessDataException {
      estateFetcher.getAllEstates();
   }

}
