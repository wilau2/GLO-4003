package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.support.BindingAwareModelMap;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.UserLoginCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions.InvalidUserLoginFieldException;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserLoginDto;
import ca.ulaval.glo4003.b6.housematch.user.model.User;
import ca.ulaval.glo4003.b6.housematch.web.converters.LoginUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.LoginUserViewModel;

public class LoginControllerTest {

   @Mock
   private UserLoginCorruptionVerificator userCorruptionVerificatior;

   @Mock
   private HttpSession session;

   @Mock
   private UserLoginDto userDto;

   @Mock
   User user;

   @Mock
   private LoginUserViewModel loginExistingUser;

   @Mock
   private LoginUserConverter converter;

   @Mock
   private HttpServletRequest request;

   @InjectMocks
   public LoginController controller;

   private BindingAwareModelMap model;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureConverter();
      configureRequest();
   }

   @Test
   public void getRequestLoginReturnsTheLoginView() {
      // Given
      model = new BindingAwareModelMap();

      // When
      String view = controller.login(model);

      // Then
      assertEquals("login", view);
   }

   @Test
   public void postRequestLoginReturnsTheIndexView() throws InvalidUserLoginFieldException {
      // Given
      model = new BindingAwareModelMap();

      // When
      String view = controller.login(request, loginExistingUser);

      // Then
      assertEquals("redirect:/", view);
   }

   @Test
   public void postRequestLoginShouldUseTheConverter() throws InvalidUserLoginFieldException {
      // When
      controller.login(request, loginExistingUser);

      // Then
      verify(converter).convertToDto(loginExistingUser);
   }

   @Test
   public void postRequestLoginShouldUseTheUserCorruptioonVerificator() throws InvalidUserLoginFieldException {
      // When
      controller.login(request, loginExistingUser);

      // Then
      verify(userCorruptionVerificatior).login(request, userDto);
   }

   @Test
   public void postRequestLoginShouldSetALoggedUser() throws InvalidUserLoginFieldException {
      // When
      controller.login(request, loginExistingUser);

      // Then
      assertEquals(loginExistingUser.getUsername(), request.getAttribute("loggedInUser"));
   }

   @Test(expected = InvalidUserLoginFieldException.class)
   public void givenInvalidUserLoginViewModelpostRequestLogingShouldThrowException()
         throws InvalidUserLoginFieldException {

      doThrow(new InvalidUserLoginFieldException()).when(userCorruptionVerificatior).login(request, userDto);
      // When
      controller.login(request, loginExistingUser);

      // Then an InvalidUserLoginFieldException is thrown
   }

   private void configureConverter() {
      given(converter.convertToDto(loginExistingUser)).willReturn(userDto);
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);
   }

}
