package ca.ulaval.glo4003.b6.housematch.estates.services;

import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;

public class EstatesFetcherTest {

   private static final String SELLER_NAME = "SELLER";

   @Mock
   private EstateRepositoryFactory estateRepositoryFactory;

   @Mock
   private EstateAssemblerFactory estateAssemblerFactory;

   @Mock
   private EstatePersistenceDtoFactory estatePersistenceDtoFactory;

   private EstatesFetcher estateFetcher;

   private EstateRepository estateRepository;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      when(estateRepositoryFactory.newInstance(estateAssemblerFactory)).thenReturn(estateRepository);

      estateFetcher = new EstatesFetcher(estateAssemblerFactory, estatePersistenceDtoFactory, estateRepositoryFactory);
   }

   @Test
   public void whenFetchingEstateBySellerNameShouldReturnListOfEstateDto() {
      // Given

      // When
      List<?> estates = estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      assertTrue(estates.get(0) instanceof EstateDto);
   }

   @Test
   public void whenFetchingEstateBySellerNameShouldGetEstateRepository() {
      // Given no changes

      // When
      estateFetcher.getEstatesBySeller(SELLER_NAME);

      // Then
      verify(estateRepositoryFactory, times(1)).newInstance(estateAssemblerFactory);
   }
}
