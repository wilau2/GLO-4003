package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandlingControllerTest {

   private static final String EXPECTED_MESSAGE = "ERROR_MESSSAGE";

   private static final String EXPECTED_VIEW_NAME = "exception";

   private ExceptionHandlingController exceptionHandlingController;

   @Mock
   private Exception exceptionThrown;

   @Mock
   private HttpServletRequest request;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      when(exceptionThrown.getMessage()).thenReturn(EXPECTED_MESSAGE);

      exceptionHandlingController = new ExceptionHandlingController();
   }

   @Test
   public void whenHandlingInvalidAccessExceptionShouldReturnExceptionViewModel() {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = exceptionHandlingController.handleInvalidAccessException(request,
            exceptionThrown);
      Map<String, Object> model = returnedModelAndView.getModel();

      // Then
      assertEquals(EXPECTED_VIEW_NAME, returnedModelAndView.getViewName());
      assertEquals(EXPECTED_MESSAGE, model.get("errorMessage"));
   }

   @Test
   public void whenHandlingInvalidContactInformationExceptionShouldReturnExceptionModel() {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = exceptionHandlingController
            .handleInvalidContactInformationFieldException(request, exceptionThrown);
      Map<String, Object> model = returnedModelAndView.getModel();

      // Then
      assertEquals(EXPECTED_VIEW_NAME, returnedModelAndView.getViewName());
      assertEquals(EXPECTED_MESSAGE, model.get("errorMessage"));
   }

   @Test
   public void whenHandleInvalidEstateFieldExceptionShouldReturnExceptionModel() {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = exceptionHandlingController.handleInvalidEstateFieldException(request,
            exceptionThrown);
      Map<String, Object> model = returnedModelAndView.getModel();

      // Then
      assertEquals(EXPECTED_VIEW_NAME, returnedModelAndView.getViewName());
      assertEquals(EXPECTED_MESSAGE, model.get("errorMessage"));
   }

   @Test
   public void whenHandlingCouldNotAccessDataExceptionShouldReturnExceptionModel() {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = exceptionHandlingController.handleCouldNotAccessDataException(request,
            exceptionThrown);
      Map<String, Object> model = returnedModelAndView.getModel();

      // Then
      assertEquals(EXPECTED_VIEW_NAME, returnedModelAndView.getViewName());
      assertEquals(EXPECTED_MESSAGE, model.get("errorMessage"));
   }

   @Test
   public void whenInvalidDescriptionExceptionShouldReturnExceptionViewModel() {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = exceptionHandlingController.handleInvalidDescriptionException(request,
            exceptionThrown);
      Map<String, Object> model = returnedModelAndView.getModel();

      // Then
      assertEquals(EXPECTED_VIEW_NAME, returnedModelAndView.getViewName());
      assertEquals(EXPECTED_MESSAGE, model.get("errorMessage"));
   }

   @Test
   public void whenInvalidDescriptionFieldExceptionShouldReturnExceptionViewModel() {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = exceptionHandlingController.handleInvalidDescriptionFieldException(request,
            exceptionThrown);
      Map<String, Object> model = returnedModelAndView.getModel();

      // Then
      assertEquals(EXPECTED_VIEW_NAME, returnedModelAndView.getViewName());
      assertEquals(EXPECTED_MESSAGE, model.get("errorMessage"));
   }

   @Test
   public void whenHandlingEstateNotFoundExceptionShouldReturnExceptionViewModel() {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = exceptionHandlingController.handleEstateNotFoundException(request,
            exceptionThrown);
      Map<String, Object> model = returnedModelAndView.getModel();

      // Then
      assertEquals(EXPECTED_VIEW_NAME, returnedModelAndView.getViewName());
      assertEquals(EXPECTED_MESSAGE, model.get("errorMessage"));
   }

   @Test
   public void whenHandlingInvalidPasswordExceptionShouldReturnExceptionViewModel() {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = exceptionHandlingController.handleInvalidPasswordException(request,
            exceptionThrown);
      Map<String, Object> model = returnedModelAndView.getModel();

      // Then
      assertEquals(EXPECTED_VIEW_NAME, returnedModelAndView.getViewName());
      assertEquals(EXPECTED_MESSAGE, model.get("errorMessage"));
   }

   @Test
   public void whenHandlingUserActivationExceptionShouldReturnExceptionViewModel() {
      // Given no changes
      String expectedRedirectedUrl = "redirect:/confirmation";

      // When
      String redirectedUrl = exceptionHandlingController.handleUserActivationException(request, exceptionThrown);

      // Then
      assertEquals(expectedRedirectedUrl, redirectedUrl);
   }

   @Test
   public void whenHandlingPictureAlreadyExistsExceptionShouldReturnExceptionViewModel() {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = exceptionHandlingController.handlePictureAlreadyExistsException(request,
            exceptionThrown);
      Map<String, Object> model = returnedModelAndView.getModel();

      // Then
      assertEquals(EXPECTED_VIEW_NAME, returnedModelAndView.getViewName());
      assertEquals(EXPECTED_MESSAGE, model.get("errorMessage"));
   }

   @Test
   public void whenUserNotFoundExceptionShouldReturnExceptionViewModel() {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = exceptionHandlingController.handleUserNotFoundException(request,
            exceptionThrown);
      Map<String, Object> model = returnedModelAndView.getModel();

      // Then
      assertEquals(EXPECTED_VIEW_NAME, returnedModelAndView.getViewName());
      assertEquals(EXPECTED_MESSAGE, model.get("errorMessage"));
   }

   @Test
   public void whenInvalidUserSignupExceptionShouldReturnExceptionViewModel() {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = exceptionHandlingController.handleInvalidUserSignupFieldException(request,
            exceptionThrown);
      Map<String, Object> model = returnedModelAndView.getModel();

      // Then
      assertEquals(EXPECTED_VIEW_NAME, returnedModelAndView.getViewName());
      assertEquals(EXPECTED_MESSAGE, model.get("errorMessage"));
   }

   @Test
   public void whenInvalidUserLoginExceptionShouldReturnExceptionViewModel() {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = exceptionHandlingController.handleInvalidUserLoginFieldException(request,
            exceptionThrown);
      Map<String, Object> model = returnedModelAndView.getModel();

      // Then
      assertEquals(EXPECTED_VIEW_NAME, returnedModelAndView.getViewName());
      assertEquals(EXPECTED_MESSAGE, model.get("errorMessage"));
   }
}
