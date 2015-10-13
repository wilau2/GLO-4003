package ca.ulaval.glo4003.b6.housematch.user.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

import javax.validation.constraints.AssertTrue;

import static org.mockito.Mockito.doNothing;

import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import edu.umd.cs.findbugs.cloud.DoNothingCloud;
import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;

public class ActivationServiceTest {
   
   private String CORRECT_USERNAME;
   private ContactInformation CONTACT_INFORMATION;
   private String PASSWORD;
   private Role ONE_ROLE;
   
   @Mock
   private UserRepository userRepository;
   
   @InjectMocks
   private ActivationService activationService;
   
   @Mock
   private User user;

   @Before
   public void setup() throws UserNotFoundException, CouldNotAccessUserDataException
   {
      MockitoAnnotations.initMocks(this);
      configureUserRepository();

   }

   private void configureUserRepository() throws UserNotFoundException, CouldNotAccessUserDataException {

      given(userRepository.getUser(CORRECT_USERNAME)).willReturn(user);  
   }
   
   @Test
   public void whenActivateUserIsActiveShouldBeTrue() throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given
      given(user.isActive()).willReturn(true);
      // When
      activationService.activateAccount(CORRECT_USERNAME);
      
      // Then
      assertEquals(user.isActive(), true);
   }
   
   @Test
   public void whenActivateAccountShouldCallUpdateUser() throws UserNotFoundException, CouldNotAccessUserDataException {
      // Given
      given(user.isActive()).willReturn(true);
      // When
      activationService.activateAccount(CORRECT_USERNAME);
      
      // Then
      verify(userRepository).updateUser(user);
   }

}
