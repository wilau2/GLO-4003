package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.dto.InformationPictureDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.UUIDAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.services.picture.PictureApprobationService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

public class AdminPicturesApprovalControllerTest {

   private static final String EXPECTED_ROLE = Role.ADMIN;

   private static final String UID = "UID";

   private AdminPicturesApprovalController adminPicturesApprovalController;

   @Mock
   private UserAuthorizationService userAuthorizationService;

   @Mock
   private PictureApprobationService inactivePictureApprover;

   @Mock
   private HttpServletRequest request;

   @Mock
   private List<Picture> pictures;

   @Mock
   private InformationPictureDto inactivePictureDto;

   @Mock
   private List<String> approvalPictureUids;

   private byte[] byteArray;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      adminPicturesApprovalController = new AdminPicturesApprovalController(inactivePictureApprover,
            userAuthorizationService);

      byteArray = new byte[10];
   }

   @Test
   public void whenGettingTheAdminPageShouldReturnTheCorrespondingString() throws InvalidAccessException {
      // Given no changes
      String expectedViewName = "admin_dashboard";

      // When
      String returnViewName = adminPicturesApprovalController.admin(request);

      // Then
      assertEquals(expectedViewName, returnViewName);
   }

   @Test(expected = InvalidAccessException.class)
   public void gettingTheAdminDashboardWhenTheUserIsUnauthorizedShouldThrowException() throws InvalidAccessException {
      // Given
      doThrow(new InvalidAccessException("")).when(userAuthorizationService).verifySessionIsAllowed(request,
            EXPECTED_ROLE);

      // When
      adminPicturesApprovalController.admin(request);

      // Then an invalid access exception is thrown
   }

   @Test
   public void whenGettingThePicturesWaitingForApprovalShouldReturnViewModel()
         throws CouldNotAccessDataException, InvalidAccessException {
      // Given no changes
      String expectedViewName = "admin_inactive_pictures";

      // When
      ModelAndView returnedModelAndView = adminPicturesApprovalController.getInactivePictures(request);

      // Then
      String returnedViewName = returnedModelAndView.getViewName();
      assertEquals(expectedViewName, returnedViewName);
   }

   @Test
   public void whenGettingThePicturesWaitingForApprovalShouldReturnPicturesInsideTheViewModel()
         throws CouldNotAccessDataException, InvalidAccessException {
      // Given
      when(inactivePictureApprover.getAllInactivePictures()).thenReturn(pictures);

      // When
      ModelAndView returnModelAndView = adminPicturesApprovalController.getInactivePictures(request);

      // Then
      Object returnedPictures = returnModelAndView.getModel().get("pictures");
      assertEquals(pictures, returnedPictures);
   }

   @Test
   public void whenGettingThePicturesWaitingForApprovalShouldReturnInstanceOfInactivePictureDtoInAlbumModel()
         throws CouldNotAccessDataException, InvalidAccessException {
      // Given no changes

      // When
      ModelAndView returnedModelAndView = adminPicturesApprovalController.getInactivePictures(request);

      // Then
      Map<String, Object> model = returnedModelAndView.getModel();
      assertTrue(model.get("album") instanceof InformationPictureDto);
   }

   @Test(expected = InvalidAccessException.class)
   public void gettingThePicturesWaitingForApporvalWhenUserUnauthorizedShouldThrowException()
         throws InvalidAccessException, CouldNotAccessDataException {
      // Given
      doThrow(new InvalidAccessException("")).when(userAuthorizationService).verifySessionIsAllowed(request,
            EXPECTED_ROLE);

      // When
      adminPicturesApprovalController.getInactivePictures(request);

      // Then an invalid access exception is thrown
   }

   @Test
   public void whenApprouvingPicturesShouldRedirectToPendingApprovalPicturesPage()
         throws CouldNotAccessDataException, InvalidAccessException, UUIDAlreadyExistsException {
      // Given
      String expectedRedirection = "redirect:/admin/pictures/";

      // When
      String returnedRedirection = adminPicturesApprovalController.approveInactivesPictures(request,
            inactivePictureDto);

      // Then
      assertEquals(expectedRedirection, returnedRedirection);
   }

   @Test(expected = InvalidAccessException.class)
   public void appouvingPicturesWhenUserIsUnauthorizedShouldThrowException()
         throws InvalidAccessException, CouldNotAccessDataException, UUIDAlreadyExistsException {
      // Given
      doThrow(new InvalidAccessException("")).when(userAuthorizationService).verifySessionIsAllowed(request,
            EXPECTED_ROLE);

      // When
      adminPicturesApprovalController.approveInactivesPictures(request, inactivePictureDto);

      // Then an invalid access exception is thrown
   }

   @Test
   public void whenApprouvingPicturesShouldCallInactivePicturesApprover()
         throws CouldNotAccessDataException, InvalidAccessException, UUIDAlreadyExistsException {
      // Given
      when(inactivePictureDto.getUidsToList()).thenReturn(approvalPictureUids);

      // When
      adminPicturesApprovalController.approveInactivesPictures(request, inactivePictureDto);

      // Then
      verify(inactivePictureApprover, times(1)).approuvePictures(approvalPictureUids);
   }

   @Test
   public void whenDeletingPicturesShouldCallInactivePicturesApprover()
         throws CouldNotAccessDataException, InvalidAccessException {
      // Given
      when(inactivePictureDto.getUidsToList()).thenReturn(approvalPictureUids);

      // When
      adminPicturesApprovalController.deleteInactivesPictures(request, inactivePictureDto);

      // Then
      verify(inactivePictureApprover, times(1)).unapprouvePictures(approvalPictureUids);
   }

   @Test
   public void whenDeletingPicturesShouldRedirectToAdminPicturesApprouvingPage()
         throws CouldNotAccessDataException, InvalidAccessException {
      // Given
      String expectedRedirection = "redirect:/admin/pictures/";

      // When
      String returnedRedirection = adminPicturesApprovalController.deleteInactivesPictures(request, inactivePictureDto);

      // Then
      assertEquals(expectedRedirection, returnedRedirection);
   }

   @Test(expected = InvalidAccessException.class)
   public void deletingPicturesWhenUserIsUnauthorizedShouldThrowException()
         throws InvalidAccessException, CouldNotAccessDataException {
      // Given
      doThrow(new InvalidAccessException("")).when(userAuthorizationService).verifySessionIsAllowed(request,
            EXPECTED_ROLE);

      // When
      adminPicturesApprovalController.deleteInactivesPictures(request, inactivePictureDto);

      // Then an invalid access exception is thrown
   }

   @Test
   public void whenGettingInactivePictureByUidShouldReturnByteArray()
         throws CouldNotAccessDataException, InvalidAccessException {
      // Given no changes
      when(inactivePictureApprover.getInactivePictureContent(UID)).thenReturn(byteArray);

      // When
      byte[] inactivePicture = adminPicturesApprovalController.getInactivePicture(UID, request);

      // Then
      assertEquals(byteArray, inactivePicture);
   }

   @Test(expected = InvalidAccessException.class)
   public void gettingInactivePicturesWhenUserIsUnauthorizedShouldThrowException()
         throws InvalidAccessException, CouldNotAccessDataException {
      // Given
      doThrow(new InvalidAccessException("")).when(userAuthorizationService).verifySessionIsAllowed(request,
            EXPECTED_ROLE);

      // When
      adminPicturesApprovalController.getInactivePicture(UID, request);

      // Then an invalid access exception is thrwon
   }
}
