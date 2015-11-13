package ca.ulaval.glo4003.b6.housematch.services.user;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.UserAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.UserAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.UserNotifyingException;
import ca.ulaval.glo4003.b6.housematch.services.user.validator.UserValidator;
import ca.ulaval.glo4003.b6.housematch.services.user.validator.UserValidatorFactory;

public class UserSignupServiceTest {

   @InjectMocks
   public UserSignupService userSignupService;

   @Mock
   private UserValidatorFactory userValidatorFactory;

   @Mock
   private UserAssemblerFactory userAssemblerFactory;

   @Mock
   private UserRepository userRepository;

   private List<UserObserver> observers;

   @Mock
   private UserValidator userValidator;

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

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureValidatorFactory();
      configureAssemblerFactory();
      observers = new ArrayList<>();
      observers.add(userObserver);
      userSignupService = new UserSignupService(userValidatorFactory, userAssemblerFactory, userRepository, observers);
   }

   @Test
   public void whenSignupShouldDelegateValidationToUserValidator()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException {
      // Given

      // When
      userSignupService.signup(userDto);

      // Then
      verify(userValidator).validate(userDto);
   }

   @Test
   public void whenSignupShouldDelegateUserValidatorCreationToFactory()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException {
      // Given

      // When
      userSignupService.signup(userDto);

      // Then
      verify(userValidatorFactory).getValidator();
   }

   @Test
   public void whenSignupShouldDelegateUserAssemblerCreationToFactory()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException {
      // Given

      // When
      userSignupService.signup(userDto);

      // Then
      verify(userAssemblerFactory).createUserAssembler();
   }

   @Test
   public void whenSignupShouldDelegateAssemblingToUserAssembler()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException {
      // Given

      // When
      userSignupService.signup(userDto);

      // Then
      verify(userAssembler).assembleUser(userDto);
   }

   @Test
   public void whenSignupShouldDelegateSavingUserToRepository()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException {
      // Given

      // When
      userSignupService.signup(userDto);

      // Then
      verify(userRepository).addUser(user);
   }

   @Test
   public void whenSignupShouldCallUpdateOnObserver()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException {
      // Given

      // When
      userSignupService.signup(userDto);

      // Then
      verify(userObserver).notifyUserChanged(user);
   }

   @Test
   public void givenValidScenarioWhenSignupShouldNotThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException {
      // Given

      // When
      userSignupService.signup(userDto);

      // Then does not throw exception
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void givenInvalidDataAccesScenarioWhenSignupShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException {
      // Given

      // When
      doThrow(new CouldNotAccessDataException(null, null)).when(userRepository).addUser(user);
      userSignupService.signup(userDto);

      // Then throw CouldNotAccessUserDataException
   }

   @Test(expected = UsernameAlreadyExistsException.class)
   public void givenAlreadyUsedUsernameWhenSignupShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, UserNotifyingException {
      // Given

      // When
      doThrow(new UsernameAlreadyExistsException(null)).when(userRepository).addUser(user);
      userSignupService.signup(userDto);

      // Then throw UsernameAlreadyExistsException
   }

   private void configureAssemblerFactory() {
      given(userAssemblerFactory.createUserAssembler()).willReturn(userAssembler);
      given(userAssembler.assembleUser(userDto)).willReturn(user);
   }

   private void configureValidatorFactory() {
      given(userValidatorFactory.getValidator()).willReturn(userValidator);
   }

}
