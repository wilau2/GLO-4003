package ca.ulaval.glo4003.b6.housematch.user.services;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.UserAssembler;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.factory.UserAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.UserValidator;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.factory.UserValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.BadEmailException;

public class UserSignupServiceTest {

   @Mock
   private UserValidatorFactory userValidatorFactory;

   @Mock
   private UserAssemblerFactory userAssemblerFactory;

   @Mock
   private UserRepository userRepository;

   @InjectMocks
   public UserSignupService userSignupService;

   @Mock
   private UserValidator userValidator;

   @Mock
   private HttpServletRequest request;

   @Mock
   private UserSignupDto userSignupDto;

   @Mock
   private UserAssembler userAssembler;

   @Mock
   private User user;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureValidatorFactory();
      configureAssemblerFactory();
   }

   @Test
   public void whenSignupShouldDelegateValidationToUserValidator()
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException, BadEmailException {
      // Given

      // When
      userSignupService.signup(userSignupDto);

      // Then
      verify(userValidator).validate(userSignupDto);
   }

   @Test
   public void whenSignupShouldDelegateUserValidatorCreationToFactory()
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException, BadEmailException {
      // Given

      // When
      userSignupService.signup(userSignupDto);

      // Then
      verify(userValidatorFactory).getValidator();
   }

   @Test
   public void whenSignupShouldDelegateUserAssemblerCreationToFactory()
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException, BadEmailException {
      // Given

      // When
      userSignupService.signup(userSignupDto);

      // Then
      verify(userAssemblerFactory).createUserAssembler();
   }

   @Test
   public void whenSignupShouldDelegateAssemblingToUserAssembler()
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException, BadEmailException {
      // Given

      // When
      userSignupService.signup(userSignupDto);

      // Then
      verify(userAssembler).assembleUser(userSignupDto);
   }

   @Test
   public void whenSignupShouldDelegateSavingUserToRepository()
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException, BadEmailException {
      // Given

      // When
      userSignupService.signup(userSignupDto);

      // Then
      verify(userRepository).addUser(user);
   }

   @Test
   public void givenValidScenarioWhenSignupShouldNotThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException, BadEmailException {
      // Given

      // When
      userSignupService.signup(userSignupDto);

      // Then does not throw exception
   }

   @Test(expected = CouldNotAccessUserDataException.class)
   public void givenInvalidDataAccesScenarioWhenSignupShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException, BadEmailException {
      // Given

      // When
      doThrow(new CouldNotAccessUserDataException(null)).when(userRepository).addUser(user);
      userSignupService.signup(userSignupDto);

      // Then throw CouldNotAccessUserDataException
   }

   @Test(expected = UsernameAlreadyExistsException.class)
   public void givenAlreadyUsedUsernameWhenSignupShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException, BadEmailException {
      // Given

      // When
      doThrow(new UsernameAlreadyExistsException(null)).when(userRepository).addUser(user);
      userSignupService.signup(userSignupDto);

      // Then throw UsernameAlreadyExistsException
   }

   private void configureAssemblerFactory() {
      given(userAssemblerFactory.createUserAssembler()).willReturn(userAssembler);
      given(userAssembler.assembleUser(userSignupDto)).willReturn(user);
   }

   private void configureValidatorFactory() {
      given(userValidatorFactory.getValidator()).willReturn(userValidator);
   }

}
