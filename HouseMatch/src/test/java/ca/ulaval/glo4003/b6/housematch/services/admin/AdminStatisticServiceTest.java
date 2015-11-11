package ca.ulaval.glo4003.b6.housematch.services.admin;

import static org.junit.Assert.assertEquals;

import static org.mockito.Matchers.any;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstatesProcessor;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstateRepositoryFactory;

public class AdminStatisticServiceTest {

   private AdminStatisticService adminStatisticService;

   @Mock
   private UserRepository userRepository;

   @Mock
   private EstateRepository estateRepository;

   @Mock
   private EstateRepositoryFactory estateRepositoryFactory;

   @Mock
   private List<Estate> estates;

   @Mock
   private EstatesProcessor estateProcessor;

   @Mock
   private List<String> uniqueSellersName;

   @Before
   public void setup() throws CouldNotAccessDataException {
      MockitoAnnotations.initMocks(this);

      adminStatisticService = new AdminStatisticService(userRepository, estateRepositoryFactory, estateProcessor);

      when(estateRepositoryFactory.newInstance(any(EstateAssemblerFactory.class))).thenReturn(estateRepository);
      when(estateRepository.getAllEstates()).thenReturn(estates);
      when(estateProcessor.retrieveUniqueSellersName(estates)).thenReturn(uniqueSellersName);
   }

   @Test
   public void whenAskingForActiveBuyerShouldFetchInformationFromRepository() throws CouldNotAccessDataException {
      // Given no changes

      // When
      adminStatisticService.getNumberOfActiveBuyer();

      // Then
      verify(userRepository, times(1)).getNumberOfActiveBuyer();
   }

   @Test
   public void whenAskingForNumberOfActiveBuyerShouldReturnInformationFromRepository()
         throws CouldNotAccessDataException {
      // Given
      int expectedNumberOfActiveBuyer = 3;
      when(userRepository.getNumberOfActiveBuyer()).thenReturn(expectedNumberOfActiveBuyer);

      // When
      int actualNumberOfActiveBuyer = adminStatisticService.getNumberOfActiveBuyer();

      // Then
      assertEquals(expectedNumberOfActiveBuyer, actualNumberOfActiveBuyer);
   }

   @Test
   public void whenAskingForNumberOfActiveSellerShouldReturnInformationFromRepository()
         throws CouldNotAccessDataException {
      // Given
      int expectedNumberOfActiveSeller = 3;
      when(uniqueSellersName.size()).thenReturn(expectedNumberOfActiveSeller);

      // When
      int numberOfActiveSeller = adminStatisticService.getNumberOfActiveSeller();

      // Then
      assertEquals(expectedNumberOfActiveSeller, numberOfActiveSeller);
   }

   @Test
   public void whenAskingForNumberOfActiveSellerShouldCallMethodOfEstateRepository()
         throws CouldNotAccessDataException {
      // Given

      // When
      adminStatisticService.getNumberOfActiveSeller();

      // Then
      verify(estateRepositoryFactory, times(1)).newInstance(any(EstateAssemblerFactory.class));
      verify(estateRepository, times(1)).getAllEstates();
   }

   @Test
   public void whenAskingForNumberOfActiveSellerShouldCallMethodOfEstatesProcessor()
         throws CouldNotAccessDataException {
      // Given

      // When
      adminStatisticService.getNumberOfActiveSeller();

      // Then
      verify(estateProcessor, times(1)).retrieveUniqueSellersName(estates);
   }

}
