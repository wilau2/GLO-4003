package ca.ulaval.glo4003.b6.housematch.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.DescriptionCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.EstateCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.PictureCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateEditDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.UUIDAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesFetcher;
import ca.ulaval.glo4003.b6.housematch.services.estate.EstatesService;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.PictureAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.services.picture.EstatePicturesService;
import ca.ulaval.glo4003.b6.housematch.services.user.UserSessionAuthorizationService;
import ca.ulaval.glo4003.b6.housematch.services.user.exceptions.InvalidAccessException;

public class SellerEstateControllerTest {

   private static final String USER_ID = "userId";

   private static final String ADDRESS = "address";

   private static final String EXPECTED_ROLE = "GOD";

   private static final String PHOTONAME = "PHOTONAME";

   private static final String SELLER = "seller";

   @InjectMocks
   private SellerEstateController estateController;

   @Mock
   private HttpServletRequest request;

   @Mock
   private EstateDto estateDto;

   @Mock
   private EstateEditDto estateEditDto;

   @Mock
   private DescriptionDto descriptionDto;

   @Mock
   private EstatesFetcher estateFetcherService;

   @Mock
   private EstatePicturesService estatePictureService;

   @Mock
   private EstateCorruptionVerificator estateCorruptionVerificator;

   @Mock
   private DescriptionCorruptionVerificator descriptionCorruptionVerificator;

   @Mock
   private PictureCorruptionVerificator pictureCorruptionVerificator;

   @Mock
   private Model model;

   @Mock
   private MultipartFile pictureFile;

   @Mock
   private UserSessionAuthorizationService userAuthorizationService;

   private List<EstateDto> estatesDto;

   @Mock
   private EstateAssemblerFactory estateAssemblerFactory;

   @Mock
   private EstatesService estatesService;

   @Before
   public void setup()
         throws SellerNotFoundException, CouldNotAccessDataException, EstateNotFoundException, InvalidAccessException {
      MockitoAnnotations.initMocks(this);

      configureFetchingListOfEstateDto();

      configureFetchingEstateByAddress();

      configureUserAuthorization();

   }

   private void configureFetchingEstateByAddress() throws EstateNotFoundException, CouldNotAccessDataException {
      when(estateFetcherService.getEstateByAddress(ADDRESS)).thenReturn(estateDto);
   }

   private void configureFetchingListOfEstateDto() throws SellerNotFoundException, CouldNotAccessDataException {
      estatesDto = new ArrayList<EstateDto>();
      estatesDto.add(estateDto);
      estatesDto.add(estateDto);
      when(estateFetcherService.getEstatesBySeller(USER_ID)).thenReturn(estatesDto);
   }

   private void configureUserAuthorization()
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      doNothing().when(userAuthorizationService).verifySessionIsAllowed(request, EXPECTED_ROLE);
   }

   @Test
   public void addingEstateFromControllerWhenEstateIsValidShouldCallEstateService() throws InvalidEstateFieldException,
         CouldNotAccessDataException, InvalidAccessException, InvalidEstateException {
      // Given no changes

      // When
      estateController.addEstate(request, estateDto, USER_ID);

      // Then
      verify(estatesService, times(1)).addEstate(estateDto);
   }

   @Test
   public void addingEstateFromControllerWhenEstateIsValidShouldReturnRedirectToString()
         throws InvalidEstateFieldException, CouldNotAccessDataException, InvalidAccessException,
         InvalidEstateException {
      // Given no changes
      String expectedRedirectTo = "redirect:/seller/" + USER_ID + "/estates";

      // When
      String returnedView = estateController.addEstate(request, estateDto, USER_ID);

      // Then
      assertEquals(expectedRedirectTo, returnedView);
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void addingEstateWhenEstateIsInvalidAddressShouldThrowException() throws InvalidEstateFieldException,
         CouldNotAccessDataException, InvalidAccessException, InvalidEstateException {
      // Given
      doThrow(new InvalidEstateFieldException("")).when(estatesService).addEstate(estateDto);

      // When
      estateController.addEstate(null, estateDto, USER_ID);

      // Then an InvalidEstateFieldException is thrown
   }

   @Test
   public void whenGettingSellerPageShouldLinkToCorrectPage() throws InvalidAccessException {
      // Given

      // When
      String redirectLink = estateController.getSellEstatePage(null, model);

      // Then
      assertEquals("sell_estate", redirectLink);
   }

   @Test
   public void whenAddingAnEstateShouldSetSellerIdIntoModel() throws InvalidEstateFieldException,
         CouldNotAccessDataException, InvalidAccessException, InvalidEstateException {
      // Given no changes

      // When
      estateController.addEstate(request, estateDto, USER_ID);

      // Then
      verify(estateDto, times(1)).setSellerId(USER_ID);
   }

   @Test
   public void whenFetchingEstateByTheLoggedSellerShouldReturnModelAndView()
         throws SellerNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      // Given

      // When
      ModelAndView returnedViewModel = estateController.getSellerEstates(USER_ID, request);

      // Then
      assertEquals("seller_estates", returnedViewModel.getViewName());

   }

   @Test
   public void whenFetchingEstateByTheLoggedSellerShouldCallFetchingMethodOfServiceLayer()
         throws SellerNotFoundException, CouldNotAccessDataException, InvalidAccessException {
      // Given

      // When
      estateController.getSellerEstates(USER_ID, request);

      // Then
      verify(estateFetcherService, times(1)).getEstatesBySeller(USER_ID);
   }

   @Test
   public void whenFetchingEstateByAddressShouldCallFetchingMethodOfServiceLayer()
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException, IOException {
      // Given no changes

      // When
      estateController.getEstateByAddress(ADDRESS, request);

      // Then
      verify(estateFetcherService, times(1)).getEstateByAddress(ADDRESS);
   }

   @Test
   public void whenFetchingEstateByAddressShouldFetchPicturesFromServiceLayer()
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidAccessException, IOException {
      // Given no changes

      // When
      estateController.getEstateByAddress(ADDRESS, request);

      // Then
      verify(estatePictureService, times(1)).getPublicPicturesOfEstate(ADDRESS);
   }

   @Test
   public void whenEditingDescriptionEstateShouldCallDescriptionCorruptionVerificator() throws EstateNotFoundException,
         CouldNotAccessDataException, InvalidAccessException, IOException, InvalidEstateFieldException,
         InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given no changes

      // When
      estateController.editDescription(ADDRESS, request, descriptionDto);

      // Then
      verify(estatesService, times(1)).editDescription(ADDRESS, descriptionDto);
   }

   @Test
   public void whenAddingPictureShouldCallAddingMethodOfServiceLayer()
         throws InvalidAccessException, CouldNotAccessDataException, InvalidEstateFieldException,
         PictureAlreadyExistsException, UUIDAlreadyExistsException, EstateNotFoundException {
      // Given no changes

      // When
      estateController.addPicture(ADDRESS, PHOTONAME, pictureFile, request);

      // Then
      verify(estatePictureService, times(1)).addPicture(ADDRESS, PHOTONAME, pictureFile);
   }

   @Test
   public void whenAddingPictureShouldVerifyAuthorization()
         throws InvalidAccessException, CouldNotAccessDataException, InvalidEstateFieldException,
         PictureAlreadyExistsException, UUIDAlreadyExistsException, EstateNotFoundException {
      // Given no changes

      // When
      estateController.addPicture(ADDRESS, PHOTONAME, pictureFile, request);

      // Then
      verify(userAuthorizationService, times(1)).verifySessionIsAllowed(request, SELLER);
   }

   @Test
   public void whenDeletingPictureShouldCallDeletingMethodOfServiceLayer()
         throws InvalidAccessException, CouldNotAccessDataException {
      // Given no changes

      // When
      estateController.deletePicture(ADDRESS, PHOTONAME, request);

      // Then
      verify(estatePictureService, times(1)).deletePicture(ADDRESS, PHOTONAME);
   }

   @Test
   public void whenDeletingPictureShouldVerifyAuthorization()
         throws InvalidAccessException, CouldNotAccessDataException {
      // Given no changes

      // When
      estateController.deletePicture(ADDRESS, PHOTONAME, request);

      // Then
      verify(userAuthorizationService, times(1)).verifySessionIsAllowed(request, SELLER);
   }

   @Test
   public void whenGettingPictureShouldCallGetMethodOfServiceLayer()
         throws InvalidAccessException, CouldNotAccessDataException {
      // Given no changes

      // When
      estateController.getPicture(ADDRESS, PHOTONAME, request);

      // Then
      verify(estatePictureService, times(1)).getPicture(ADDRESS, PHOTONAME);
   }

   @Test
   public void whenFetchingEstateByAddressShouldReturnModelAndViewOfWantedEstate() throws EstateNotFoundException,
         CouldNotAccessDataException, InvalidAccessException, CouldNotAccessDataException, IOException {
      // Given
      String expectedViewName = "estate";

      // When
      ModelAndView returnedModelAndView = estateController.getEstateByAddress(ADDRESS, request);

      // Then
      assertEquals(expectedViewName, returnedModelAndView.getViewName());
      Map<String, Object> returnedModel = returnedModelAndView.getModel();
      assertTrue(returnedModel.get("estate") instanceof EstateDto);
   }

   @Test
   public void editingEstateFromControllerWhenEstateIsValidShouldCallEstateService() throws InvalidEstateFieldException,
         CouldNotAccessDataException, InvalidAccessException, EstateNotFoundException, InvalidEstateException {
      // Given no changes

      // When
      estateController.editEstate(ADDRESS, request, estateEditDto);

      // Then
      verify(estatesService, times(1)).editEstate(ADDRESS, estateEditDto);
   }

   @Test
   public void editingEstateFromControllerWhenEstateIsValidShouldReturnRedirectToString()
         throws InvalidEstateFieldException, CouldNotAccessDataException, InvalidAccessException,
         EstateNotFoundException, InvalidEstateException {
      // Given no changes
      String expectedRedirectTo = "redirect:/seller/{" + USER_ID + "}/estates/{" + ADDRESS + "}";

      // When
      String returnedView = estateController.editEstate(ADDRESS, request, estateEditDto);

      // Then
      assertEquals(expectedRedirectTo, returnedView);
   }

   @Test
   public void editingDescriptionFromControllerWhenDescriptionIsValidShouldCallEstateService()
         throws CouldNotAccessDataException, InvalidAccessException, EstateNotFoundException,
         InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given no changes

      // When
      estateController.editDescription(ADDRESS, request, descriptionDto);

      // Then
      verify(estatesService, times(1)).editDescription(ADDRESS, descriptionDto);
   }

   @Test
   public void editingDescriptionFromControllerWhenEstateIsValidShouldReturnRedirectToString()
         throws CouldNotAccessDataException, InvalidAccessException, EstateNotFoundException,
         InvalidDescriptionFieldException, InvalidDescriptionException, InvalidEstateException {
      // Given no changes
      String expectedRedirectTo = "redirect:/seller/{" + USER_ID + "}/estates/{" + ADDRESS + "}";

      // When
      String returnedView = estateController.editDescription(ADDRESS, request, descriptionDto);

      // Then
      assertEquals(expectedRedirectTo, returnedView);
   }

}
