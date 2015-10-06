package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.user.anticorruption.UserProfilCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.services.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.user.services.UserFetcher;
import ca.ulaval.glo4003.b6.housematch.user.services.exceptions.InvalidAccessException;
import ca.ulaval.glo4003.b6.housematch.web.converters.ProfilUserConverter;
import ca.ulaval.glo4003.b6.housematch.web.viewModel.ProfilUserViewModel;

public class ProfilUserControllerTest {

   @Mock
   private UserAuthorizationService userAuthorizationService;

   @Mock
   private UserProfilCorruptionVerificator userProfilCorruptionVerificator;

   @Mock
   private ProfilUserConverter profilUserConverter;

   @Mock
   private UserFetcher userFetcher;

   @Mock
   private HttpServletRequest request;

   private String expectedRole = "buyer";

   @Mock
   private UserDetailedDto userDto;

   @Mock
   private ProfilUserViewModel viewModel;

   @InjectMocks
   public ProfilUserController profilUserController;

   @Mock
   private HttpSession session;

   @Mock
   private Object username;

   private final static String USERNAME = "username";

   @Before
   public void setup() throws InvalidAccessException, UserNotFoundException, CouldNotAccessUserDataException {
      MockitoAnnotations.initMocks(this);
      configureUserAuthorizationService();
      configureUserProfilCorruptionVerificator();
      configureProfilUserConverter();
      configureUserFetcher();
      configureRequest();
      configureSession();
   }

   private void configureSession() {
      given(session.getAttribute(UserAuthorizationService.LOGGED_IN_USERNAME)).willReturn(username);
   }

   private void configureRequest() {
      given(request.getSession()).willReturn(session);
   }

   private void configureUserFetcher() throws UserNotFoundException, CouldNotAccessUserDataException {
      given(userFetcher.getUserByUsername(USERNAME)).willReturn(userDto);

   }

   private void configureProfilUserConverter() {
      given(profilUserConverter.convertToViewModel(userDto)).willReturn(viewModel);

   }

   private void configureUserProfilCorruptionVerificator() {
      // TODO Auto-generated method stub

   }

   private void configureUserAuthorizationService() throws InvalidAccessException {
      given(userAuthorizationService.isSessionAllowed(request, expectedRole)).willReturn(true);
   }

   @Test
   public void givenValidAccessWhenGetProfilShouldDelegateAccessValidation()
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      profilUserController.getProfil(request);

      // Then
      verify(userAuthorizationService, times(1)).isSessionAllowed(request, expectedRole);
   }

   @Test
   public void givenValidUserWhenGetProfilShouldDelegateGettingUser()
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      profilUserController.getProfil(request);

      // Then
      verify(userFetcher, times(1)).getUserByUsername(username.toString());
   }

   @Test
   public void givenValidUserWhenGetProfilShouldConvertingUserDto()
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      profilUserController.getProfil(request);

      // Then
      verify(profilUserConverter, times(1)).convertToViewModel(userDto);
   }

   @Test
   public void givenValidAccessWhenGetProfilShouldReturnProfilView()
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      ModelAndView returnedViewModel = profilUserController.getProfil(request);

      // Then
      assertEquals("profil", returnedViewModel.getViewName());
   }

   @Test
   public void givenValidAccessWhenGetProfilShouldAddObjectToView()
         throws InvalidAccessException, UserNotFoundException, CouldNotAccessUserDataException {
      // Given

      // When
      ModelAndView returnedViewModel = profilUserController.getProfil(request);

      // Then
      assertEquals(viewModel, returnedViewModel.getModel().get("user"));
   }

   @Test(expected = InvalidAccessException.class)
   public void givenInvalidAccessWhenGetProfilShouldThrowException()
         throws UserNotFoundException, CouldNotAccessUserDataException, InvalidAccessException {

      // Given
      doThrow(new InvalidAccessException("")).when(userAuthorizationService).isSessionAllowed(request, expectedRole);

      // When
      profilUserController.getProfil(request);

      // Then Exception is thrown

   }

   @Test(expected = UserNotFoundException.class)
   public void givenUserDoesNotExistWhenGetProfilShouldThrowException()
         throws UserNotFoundException, CouldNotAccessUserDataException, InvalidAccessException {

      // Given
      doThrow(new UserNotFoundException("")).when(userFetcher).getUserByUsername(USERNAME);

      // When
      profilUserController.getProfil(request);

      // Then Exception is thrown

   }

   @Test(expected = CouldNotAccessUserDataException.class)
   public void givenCouldNotAccessDataWhenGetProfilShouldThrowException()
         throws UserNotFoundException, CouldNotAccessUserDataException, InvalidAccessException {

      // Given
      doThrow(new CouldNotAccessUserDataException("")).when(userFetcher).getUserByUsername(USERNAME);

      // When
      profilUserController.getProfil(request);

      // Then Exception is thrown

   }

   @Test
   public void shouldDoThisWhenThat() {
      // Given

      // When

      // Then
   }

}
