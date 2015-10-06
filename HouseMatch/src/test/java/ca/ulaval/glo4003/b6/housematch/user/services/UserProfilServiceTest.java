package ca.ulaval.glo4003.b6.housematch.user.services;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.UserAssembler;
import ca.ulaval.glo4003.b6.housematch.user.domain.assembler.factory.UserAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.UserValidator;
import ca.ulaval.glo4003.b6.housematch.user.dto.validators.factory.UserValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.user.repository.UserRepository;

public class UserProfilServiceTest {

   @Mock
   private UserRepository userRepository;

   @Mock
   private UserValidatorFactory userValidatorFactory;

   @Mock
   private UserAssemblerFactory userAssemblerFactory;

   @InjectMocks
   private UserProfilService userProfilService;

   @Mock
   private UserAssembler userAssembler;

   @Mock
   private UserValidator userValidator;

   @Mock
   private UserDetailedDto userDto;

   @Mock
   private User user;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureUserValidator();
      configureUserAssembler();
   }

   private void configureUserAssembler() {
      given(userAssemblerFactory.createUserAssembler()).willReturn(userAssembler);
      given(userAssembler.assembleUser(userDto)).willReturn(user);
   }

   private void configureUserValidator() {
      given(userValidatorFactory.getValidator()).willReturn(userValidator);
   }

   @Test
   public void givenValidUserDtoWhenUpdateThenShouldDelegateToUserRepository() {
      // Given

      // When
      userProfilService.update(userDto);

      // Then
      verify(userRepository, times(1)).updateUser(user);
   }

   @Test
   public void givenValidUserDtoWhenUpdateThenShouldDelegateAssembling() {
      // Given

      // When
      userProfilService.update(userDto);

      // Then
      verify(userAssembler, times(1)).assembleUser(userDto);
   }

   @Test
   public void givenValidUserDtoWhenUpdateThenShouldDelegateValidation() {
      // Given

      // When
      userProfilService.update(userDto);

      // Then
      verify(userValidator, times(1)).validate(userDto);
   }

}
