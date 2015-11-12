package ca.ulaval.glo4003.b6.housematch.services.admin;

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

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserProcessor;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class AdminStatisticServiceTest {

   private AdminStatisticService adminStatisticService;

   @Mock
   private UserRepository userRepository;

   @Mock
   private List<User> users;

   @Mock
   private UserProcessor userProcessor;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);

      adminStatisticService = new AdminStatisticService(userRepository, userProcessor);

      when(userRepository.getAllUser()).thenReturn(users);
   }

   @Test
   public void whenAskingForActiveBuyerShouldFetchInformationFromRepository()
         throws CouldNotAccessDataException, DocumentException {
      // Given no changes

      // When
      adminStatisticService.getNumberOfActiveBuyer();

      // Then
      verify(userRepository, times(1)).getAllUser();
      verify(userProcessor, times(1)).getNumberOfActiveBuyer(users);
   }

   @Test
   public void whenAskingForNumberOfActiveBuyerShouldReturnInformationFromRepository()
         throws CouldNotAccessDataException, DocumentException {
      // Given
      int expectedNumberOfActiveBuyer = 3;
      when(userProcessor.getNumberOfActiveBuyer(users)).thenReturn(expectedNumberOfActiveBuyer);

      // When
      int actualNumberOfActiveBuyer = adminStatisticService.getNumberOfActiveBuyer();

      // Then
      assertEquals(expectedNumberOfActiveBuyer, actualNumberOfActiveBuyer);
   }
}
