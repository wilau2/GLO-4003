package ca.ulaval.glo4003.b6.housematch.domain.user;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.persistance.exceptions.CouldNotAccessDataException;

public class UserProcessorTest {

   private UserProcessor userProcessor;

   @Mock
   private User user;

   private List<User> users;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      userProcessor = new UserProcessor();

      users = new ArrayList<User>();
   }

   @Test
   public void whenAskingNumberOfActiveBuyerShouldVerifyIfUserHasBuyerRole()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException {
      // Given
      int numberOfUserInSystem = 3;
      configureUsers(numberOfUserInSystem);
      when(user.isBuyer()).thenReturn(true);

      // When
      userProcessor.getNumberOfActiveBuyer(users);

      // Then
      verify(user, times(numberOfUserInSystem)).isBuyer();
   }

   private void configureUsers(int numberOfUserInSystem) {

      for (int i = 0; i < numberOfUserInSystem; i++) {
         users.add(user);
      }
   }

   @Test
   public void whenAskingTheNumberOfActiveBuyerShouldCallMethodsOnUser() throws CouldNotAccessDataException {
      // Given
      int numberOfUserInSystem = 1;
      configureUsers(numberOfUserInSystem);
      when(user.isBuyer()).thenReturn(true);
      when(user.isActive()).thenReturn(true);

      // When
      userProcessor.getNumberOfActiveBuyer(users);

      // Then
      verify(user, times(1)).wasActiveInTheLastSixMonths();
   }

   @Test
   public void askingNumberOfActiveBuyerWhenNotAllBuyerIsActiveShouldReturnNumberOfActiveBuyer()
         throws CouldNotAccessDataException {
      // Given
      int numberOfUserInSystem = 3;
      configureUsers(numberOfUserInSystem);
      when(user.isBuyer()).thenReturn(true);
      when(user.wasActiveInTheLastSixMonths()).thenReturn(true, false, true);
      int expectedNumberOfActiveBuyer = 2;

      // When
      int actualNumberOfActiveBuyer = userProcessor.getNumberOfActiveBuyer(users);

      // Then
      assertEquals(expectedNumberOfActiveBuyer, actualNumberOfActiveBuyer);
   }

}
