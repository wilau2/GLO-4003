package ca.ulaval.glo4003.b6.housematch.services.estate;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
      configureEstateAssembler();
      configureFetchingEstateByAddress();
      configureEstateProcessor();
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
   public void whenFetchingEstateBySellerNameShouldReturnListOfEstateDto()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given no changes

      // When
      List<EstateDto> estates = estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      assertTrue(estates instanceof List<?>);
   }

   @Test
   public void whenFetchingEstatesBySellerNameShouldCallMethodFromRepository()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      verify(estateRepository, times(1)).getAllEstates();
   }

   @Test
   public void whenFetchingEstateBySellerNameShouldCallEstateAssemblerForDto()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given
      int numberOfReturnedEstateFromRepo = estates.size();

      // When
      estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      verify(estateAssemblerFactory, times(1)).createEstateAssembler();
      verify(estateAssembler, times(numberOfReturnedEstateFromRepo)).assembleEstateDto(estate);

   }

   @Test
   public void whenFetchingEstateByAddressShouldReturnEstateDto()
         throws EstateNotFoundException, CouldNotAccessDataException {
      // Given no changes

      // When
      Object returnedObject = estateFetcher.getEstateByAddress(ADDRESS);

      // Then
      assertTrue(returnedObject instanceof EstateDto);
   }

   @Test
   public void fetchingAnEstateByAddressWhenOneEstateHasItsAddressShouldCallRepository()
         throws EstateNotFoundException, CouldNotAccessDataException {
      // Given no changes

      // When
      estateFetcher.getEstateByAddress(ADDRESS);

      // Then
      verify(estateRepository, times(1)).getEstateByAddress(ADDRESS);
   }

   @Test
   public void fetchingAnEstateByItsAddressWhenAddressCorrespondToAnEstateShouldConvertEstateToEstateDto()
         throws EstateNotFoundException, CouldNotAccessDataException {
      // Given no changes

      // When
      estateFetcher.getEstateByAddress(ADDRESS);

      // Then
      verify(estateAssemblerFactory, times(1)).createEstateAssembler();
      verify(estateAssembler, times(1)).assembleEstateDto(estate);
   }

   @Test
   public void whenFetchingAllEstatesShouldReturnListEstateDto()
         throws EstateNotFoundException, CouldNotAccessDataException {
      // Given no changes

      // When
      List<EstateDto> estates = estateFetcher.getAllEstates();

      // Then
      assertTrue(estates instanceof List<?>);
   }

   @Test
   public void whenAskedAllEstatesShouldCallAssembleEstate() throws CouldNotAccessDataException {
      // given
      List<Estate> dumbEstateDtoList = new ArrayList<Estate>();
      dumbEstateDtoList.add(estate);
      Estates estates = new Estates(dumbEstateDtoList);
      when(estateRepository.getAllEstates()).thenReturn(estates);

      // when
      estateFetcher.getAllEstates();

      // then
      verify(estateRepository, times(1)).getAllEstates();
   }

   @Test
   public void whenAskedAllEstatesShouldCallEstateAssembleDtoWithEstate() throws CouldNotAccessDataException {
      // given

      // when
      estateFetcher.getAllEstates();

      // then
      verify(estateAssembler).assembleEstatesDto(estates);
   }

   @Test
   public void whenGetPriceOrderedAscendentShouldReturnListEstate() throws CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getAllEstates();
      List<EstateDto> listDto = estateFetcher.getPriceOrderedAscendantEstates();

      // Then
      assertTrue(listDto instanceof List<?>);
   }

   @Test
   public void whenGetPriceOrderedDescendantShouldReturnListEstate() throws CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getAllEstates();
      List<EstateDto> listDto = estateFetcher.getPriceOrderedDescendantEstates();

      // Then
      assertTrue(listDto instanceof List<?>);
   }

   @Test
   public void whenGetDateOrderedAscendantShouldReturnListEstate() throws CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getAllEstates();
      List<EstateDto> listDto = estateFetcher.getDateOrderedAscendantEstates();

      // Then
      assertTrue(listDto instanceof List<?>);
   }

   @Test
   public void whenGetDateOrderedDescendantShouldReturnListEstate() throws CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getAllEstates();
      List<EstateDto> listDto = estateFetcher.getDateOrderedDescendantEstates();

      // Then
      assertTrue(listDto instanceof List<?>);
   }
}
