package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.support.BindingAwareModelMap;

import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.user.model.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.XMLUserRepository;
import ca.ulaval.glo4003.b6.housematch.user.service.UserLoginService;
import ca.ulaval.glo4003.b6.housematch.web.converters.LoginUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.LoginUserViewModel;

public class LoginControllerTest {

   @Mock
   private UserLoginService userLoginService;

   @Mock
   private HttpSession session;

   @Mock
   private UserDto userDto;

   @Mock
   User user;

   @Mock
   private LoginUserViewModel loginExistingUser;

   @Mock
   private XMLUserRepository userRepository;

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
      configureRepository();
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
   public void postRequestLoginReturnsTheIndexView() {
      model = new BindingAwareModelMap();

      String view = controller.login(request, loginExistingUser);

      assertEquals("redirect:/", view);
   }

   @Test
   public void postRequestLoginShouldUseTheConverter() {
      controller.login(request, loginExistingUser);

      verify(converter).convertToDto(loginExistingUser);
   }

   @Test
   public void postRequestLoginShouldUseTheUserLoginService() {
      controller.login(request, loginExistingUser);

      verify(userLoginService).serviceMethod(request, userDto);
   }

   @Test
   public void postRequestLoginShouldSetALoggedUser() {
      controller.login(request, loginExistingUser);
      assertEquals(loginExistingUser.getUsername(), request.getAttribute("loggedInUser"));
   }

   private void configureConverter() {
      given(converter.convertToDto(loginExistingUser)).willReturn(userDto);
   }

   private void configureRepository() {
      given(userRepository.findByEmail(userDto.getEmail())).willReturn(user);
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);
   }

}
