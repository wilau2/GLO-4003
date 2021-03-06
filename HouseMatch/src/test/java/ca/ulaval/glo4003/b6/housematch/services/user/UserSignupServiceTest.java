package ca.ulaval.glo4003.b6.housematch.services.user;

import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.anticorruption.user.UserSignupCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidContactInformationFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions.InvalidUserSignupFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.UserAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.UserAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;

public class UserSignupServiceTest {

   @InjectMocks
   public UserSignupService userSignupService;

   @Mock
   private UserAssemblerFactory userAssemblerFactory;

   @Mock
   private UserRepository userRepository;

   private List<UserObserver> observers;

   @Mock
   private HttpServletRequest request;

   @Mock
   private UserDto userDto;

   @Mock
   private UserAssembler userAssembler;

   @Mock
   private User user;

   @Mock
   private UserObserver userObserver;

   @Mock
   private UserSignupCorruptionVerificator userSignupCorruptionVerificator;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureAssemblerFactory();
      observers = new ArrayList<>();
      observers.add(userObserver);
      userSignupService = new UserSignupService(userSignupCorruptionVerificator, userAssemblerFactory, userRepository,
            observers);
   }

   @Test
   public void whenSignupShouldDelegateUserAssemblerCreationToFactory()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException,
         InvalidUserSignupFieldException, InvalidContactInformationFieldException {
      // Given

      // When
      userSignupService.signup(userDto);

      // Then
      verify(userAssemblerFactory).createUserAssembler();
   }

   @Test
   public void whenSignupShouldDelegateAssemblingToUserAssembler()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException,
         InvalidUserSignupFieldException, InvalidContactInformationFieldException {
      // Given

      // When
      userSignupService.signup(userDto);

      // Then
      verify(userAssembler).assembleUser(userDto);
   }

   @Test
   public void whenSignupShouldDelegateSavingUserToRepository()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException,
         InvalidUserSignupFieldException, InvalidContactInformationFieldException {
      // Given

      // When
      userSignupService.signup(userDto);

      // Then
      verify(userRepository).add(user);
   }

   @Test
   public void whenSignupShouldCallUpdateOnObserver()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException,
         InvalidUserSignupFieldException, InvalidContactInformationFieldException {
      // Given

      // When
      userSignupService.signup(userDto);

      // Then
      verify(userObserver).notifyUserChanged(user);
   }

   @Test
   public void givenValidScenarioWhenSignupShouldNotThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException,
         InvalidUserSignupFieldException, InvalidContactInformationFieldException {
      // Given no changes

      // When
      userSignupService.signup(userDto);

      // Then does not throw exception
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void givenInvalidDataAccesScenarioWhenSignupShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException,
         InvalidUserSignupFieldException, InvalidContactInformationFieldException {
      // Given
      doThrow(new CouldNotAccessDataException(null, null)).when(userRepository).add(user);

      // When
      userSignupService.signup(userDto);

      // Then throw CouldNotAccessUserDataException
   }

   @Test(expected = UsernameAlreadyExistsException.class)
   public void givenAlreadyUsedUsernameWhenSignupShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException,
         InvalidUserSignupFieldException, InvalidContactInformationFieldException {
      // Given
      doThrow(new UsernameAlreadyExistsException(null)).when(userRepository).add(user);

      // When
      userSignupService.signup(userDto);

      // Then throw UsernameAlreadyExistsException
   }

   @Test
   public void whenSignupNewUserShouldCallCorruptionVerificator()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessDataException,
         InvalidContactInformationFieldException, UserNotifyingException {
      // Given no changes

      // When
      userSignupService.signup(userDto);

      // Then
      verify(userSignupCorruptionVerificator, times(1)).validateSignup(userDto);
   }

   @Test(expected = InvalidUserSignupFieldException.class)
   public void addingNewUserWhenUserSignUpCorruptionVerificatorThrowInvalidFieldExceptionShouldThrowException()
         throws InvalidUserSignupFieldException, UsernameAlreadyExistsException, CouldNotAccessDataException,
         InvalidContactInformationFieldException, UserNotifyingException {
      // Given
      doThrow(new InvalidUserSignupFieldException("")).when(userSignupCorruptionVerificator).validateSignup(userDto);

      // When
      userSignupService.signup(userDto);

      // Then an invalid user signup field exception is thrown
   }

   private void configureAssemblerFactory() {
      given(userAssemblerFactory.createUserAssembler()).willReturn(userAssembler);
      given(userAssembler.assembleUser(userDto)).willReturn(user);
   }

}
