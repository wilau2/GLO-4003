package ca.ulaval.glo4003.b6.housematch.estates.services;

import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class EstatesFetcherTest {

   private static final String SELLER_NAME = "SELLER";

   @Mock
   private EstateRepositoryFactory estateRepositoryFactory;

   @Mock
   private EstateAssemblerFactory estateAssemblerFactory;

   @Mock
   private EstateRepository estateRepository;

   private List<Estate> estates;

   @Mock
   private Estate estate;

   @Mock
   private EstateAssembler estateAssembler;

   @Mock
   private EstateDto estateDto;

   private EstatesFetcher estateFetcher;

   @Before
   public void setup() throws SellerNotFoundException, CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);

      configureEstateRepository();

      configureEstateAssembler();

      estateFetcher = new EstatesFetcher(estateAssemblerFactory, estateRepositoryFactory);
   }

   private void configureEstateAssembler() {
      when(estateAssemblerFactory.createEstateAssembler()).thenReturn(estateAssembler);
      when(estateAssembler.assembleEstateDto(estate)).thenReturn(estateDto);
   }

   private void configureEstateRepository() throws SellerNotFoundException, CouldNotAccessDataException {
      estates = new ArrayList<Estate>();
      estates.add(estate);
      when(estateRepositoryFactory.newInstance(estateAssemblerFactory)).thenReturn(estateRepository);
      when(estateRepository.getEstateFromSeller(SELLER_NAME)).thenReturn(estates);
   }

   @Test
   public void whenFetchingEstateBySellerNameShouldReturnListOfEstateDto()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      List<?> estates = estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      assertTrue(estates.get(0) instanceof EstateDto);
   }

   @Test
   public void whenFetchingEstateBySellerNameShouldGetEstateRepository()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given no changes

      // When
      estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      verify(estateRepositoryFactory, times(1)).newInstance(estateAssemblerFactory);
   }

   @Test
   public void whenFetchingEstatesBySellerNameShouldCallMethodFromRepository()
         throws SellerNotFoundException, CouldNotAccessDataException {
      // Given

      // When
      estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      verify(estateRepository, times(1)).getEstateFromSeller(SELLER_NAME);
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

}
