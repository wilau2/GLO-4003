package ca.ulaval.glo4003.b6.housematch.web.converters;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDto;
import ca.ulaval.glo4003.b6.housematch.web.viewmodels.ProfilUserViewModel;

public class ProfilUserConverterTest {

   private static final String USERNAME = "username";

   private static final String PHONE_NUMBER = "123123123";

   private static final String EMAIL = "email";

   private static final String FIRST_NAME = "firstname";

   private static final String LAST_NAME = "lastname";

   @InjectMocks
   private ProfilUserConverter profilUserConverter;

   @Mock
   private UserDto user;

   @Mock
   private ProfilUserViewModel profilUserViewModel;

   @Mock
   private ContactInformationDto contactInformationDto;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

   }

   private void configureValidUser() {
      given(user.getUsername()).willReturn(USERNAME);
      given(user.getContactInformationDto()).willReturn(contactInformationDto);
      given(contactInformationDto.getPhoneNumber()).willReturn(PHONE_NUMBER);
      given(contactInformationDto.getEmail()).willReturn(EMAIL);
      given(contactInformationDto.getFirstName()).willReturn(FIRST_NAME);
      given(contactInformationDto.getLastName()).willReturn(LAST_NAME);
   }

   private void configureValidViewModel() {
      given(profilUserViewModel.getUsername()).willReturn(USERNAME);
      given(profilUserViewModel.getPhoneNumber()).willReturn(PHONE_NUMBER);
      given(profilUserViewModel.getEmail()).willReturn(EMAIL);
      given(profilUserViewModel.getFirstName()).willReturn(FIRST_NAME);
      given(profilUserViewModel.getLastName()).willReturn(LAST_NAME);
   }

   @Test
   public void givenValidUserWhenConvertToViewModelThenViewModelUsernameIsGood() {
      // Given
      configureValidUser();
      // When
      ProfilUserViewModel viewModel = profilUserConverter.convertToViewModel(user);

      // Then
      assertEquals(USERNAME, viewModel.getUsername());
   }

   @Test
   public void givenValidUserWhenConvertToViewModelThenViewModelPhoneNumberIsGood() {
      // Given
      configureValidUser();
      // When
      ProfilUserViewModel viewModel = profilUserConverter.convertToViewModel(user);

      // Then
      assertEquals(PHONE_NUMBER, viewModel.getPhoneNumber());
   }

   @Test
   public void givenValidUserWhenConvertToViewModelThenViewModelEmailIsGood() {
      // Given
      configureValidUser();
      // When
      ProfilUserViewModel viewModel = profilUserConverter.convertToViewModel(user);

      // Then
      assertEquals(EMAIL, viewModel.getEmail());
   }

   @Test
   public void givenValidUserWhenConvertToViewModelThenViewModelFirsNameIsGood() {
      // Given
      configureValidUser();
      // When
      ProfilUserViewModel viewModel = profilUserConverter.convertToViewModel(user);

      // Then
      assertEquals(FIRST_NAME, viewModel.getFirstName());
   }

   @Test
   public void givenValidUserWhenConvertToViewModelThenViewModelLastNameIsGood() {
      // Given
      configureValidUser();
      // When
      ProfilUserViewModel viewModel = profilUserConverter.convertToViewModel(user);

      // Then
      assertEquals(LAST_NAME, viewModel.getLastName());
   }

   @Test
   public void givenValidUserWhenConvertViewModelToDtoThenViewModelUsernameIsGood() {
      // Given
      configureValidViewModel();
      // When
      UserDto dto = profilUserConverter.convertViewModelToDto(profilUserViewModel);

      // Then
      assertEquals(USERNAME, dto.getUsername());
   }

   @Test
   public void givenValidUserWhenConvertViewModelToDtoThenViewModelPhoneNumberIsGood() {
      // Given
      configureValidViewModel();
      // When
      UserDto dto = profilUserConverter.convertViewModelToDto(profilUserViewModel);

      // Then
      assertEquals(PHONE_NUMBER, dto.getContactInformationDto().getPhoneNumber());
   }

   @Test
   public void givenValidUserWhenConvertViewModelToDtoThenViewModelEmailIsGood() {
      // Given
      configureValidViewModel();
      // When
      UserDto dto = profilUserConverter.convertViewModelToDto(profilUserViewModel);

      // Then
      assertEquals(EMAIL, dto.getContactInformationDto().getEmail());
   }

   @Test
   public void givenValidUserWhenConvertViewModelToDtoThenViewModelFirstNameIsGood() {
      // Given
      configureValidViewModel();
      // When
      UserDto dto = profilUserConverter.convertViewModelToDto(profilUserViewModel);

      // Then
      assertEquals(FIRST_NAME, dto.getContactInformationDto().getFirstName());
   }

   @Test
   public void givenValidUserWhenConvertViewModelToDtoThenViewModelLastNameIsGood() {
      // Given
      configureValidViewModel();
      // When
      UserDto dto = profilUserConverter.convertViewModelToDto(profilUserViewModel);

      // Then
      assertEquals(LAST_NAME, dto.getContactInformationDto().getLastName());
   }

}
