package ca.ulaval.glo4003.b6.housematch.services.admin;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class AdminStatisticServiceTest {

   private AdminStatisticService adminStatisticService;

   @Mock
   private UserRepository userRepository;

   @Mock
   private UserRepositoryFactory userRepositoryFactory;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      adminStatisticService = new AdminStatisticService(userRepositoryFactory);

      when(userRepositoryFactory.newInstance()).thenReturn(userRepository);
   }

   @Test
   public void whenAskingForActiveBuyerShouldFetchInformationFromRepository() throws CouldNotAccessDataException {
      // Given no changes

      // When
      adminStatisticService.getNumberOfActiveBuyer();

      // Then
      verify(userRepositoryFactory, times(1)).newInstance();
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
}
