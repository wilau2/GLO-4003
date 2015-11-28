package ca.ulaval.glo4003.b6.housematch.services.statistic;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstatesProcessor;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserProcessor;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class StatisticServiceTest {

   private StatisticService adminStatisticService;

   @Mock
   private UserRepository userRepository;

   @Mock
   private List<User> users;

   @Mock
   private UserProcessor userProcessor;

   @Mock
   private EstateRepository estateRepository;

   @Mock
   private List<Estate> estates;

   @Mock
   private EstatesProcessor estateProcessor;

   @Mock
   private List<String> uniqueSellersName;

   @Before
   public void setup() throws CouldNotAccessDataException, DocumentException {
      MockitoAnnotations.initMocks(this);

      adminStatisticService = new StatisticService(userRepository, userProcessor, estateRepository, estateProcessor);

      when(userRepository.getAllUsers()).thenReturn(users);

      when(estateRepository.getAllEstates()).thenReturn(estates);
      when(estateProcessor.retrieveUniqueSellersName(estates)).thenReturn(uniqueSellersName);

   }

   @Test
   public void whenAskingForActiveBuyerShouldFetchInformationFromRepository()
         throws CouldNotAccessDataException, DocumentException {
      // Given no changes

      // When
      adminStatisticService.getNumberOfActiveBuyers();

      // Then
      verify(userRepository, times(1)).getAllUsers();
      verify(userProcessor, times(1)).getNumberOfActiveBuyer(users);
   }

   @Test
   public void whenAskingForNumberOfActiveBuyerShouldReturnInformationFromRepository()
         throws CouldNotAccessDataException, DocumentException {
      // Given
      int expectedNumberOfActiveBuyer = 3;
      when(userProcessor.getNumberOfActiveBuyer(users)).thenReturn(expectedNumberOfActiveBuyer);

      // When
      int actualNumberOfActiveBuyer = adminStatisticService.getNumberOfActiveBuyers();

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
      int numberOfActiveSeller = adminStatisticService.getNumberOfActiveSellers();

      // Then
      assertEquals(expectedNumberOfActiveSeller, numberOfActiveSeller);
   }

   @Test
   public void whenAskingForNumberOfActiveSellerShouldCallMethodOfEstateRepository()
         throws CouldNotAccessDataException {
      // Given

      // When
      adminStatisticService.getNumberOfActiveSellers();

      // Then
      verify(estateRepository, times(1)).getAllEstates();
   }

   @Test
   public void whenAskingForNumberOfActiveSellerShouldCallMethodOfEstatesProcessor()
         throws CouldNotAccessDataException {
      // Given

      // When
      adminStatisticService.getNumberOfActiveSellers();

      // Then
      verify(estateProcessor, times(1)).retrieveUniqueSellersName(estates);
   }

}
