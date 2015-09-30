package ca.ulaval.glo4003.b6.housematch.user.domain.assembler;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserDetailedDto;
import ca.ulaval.glo4003.b6.housematch.user.dto.UserSignupDto;

public class UserAssemblerTest {

   private static final String FIRST_NAME = "firstname";

   private static final String LAST_NAME = "lastname";

   private static final String EMAIL = "email";

   private static final String PHONE_NUMBER = "phonenumber";

   private static final String USERNAME = "username";

   private static final String ROLE = "role";

   private static final String PASSWORD = "password";

   @InjectMocks
   UserAssembler userAssembler;

   @Mock
   private UserSignupDto userSignupDto;

   @Mock
   private User userToConvert;

   @Mock
   private ContactInformation contactInformation;

   @Mock
   private Role role;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureValidUserSignupDto();
   }

   @Test
   public void givenValidUserSignupDtoWhenAssembleUserShouldGetFirstName() {
      // Given

      // When
      userAssembler.assembleUser(userSignupDto);

      // Then
      verify(userSignupDto).getFirstName();
   }

   @Test
   public void givenValidUserSignupDtoWhenAssembleUserShouldGetLastName() {
      // Given

      // When
      userAssembler.assembleUser(userSignupDto);

      // Then
      verify(userSignupDto).getLastName();
   }

   @Test
   public void givenValidUserSignupDtoWhenAssembleUserShouldGetEmail() {
      // Given

      // When
      userAssembler.assembleUser(userSignupDto);

      // Then
      verify(userSignupDto).getEmail();
   }

   @Test
   public void givenValidUserSignupDtoWhenAssembleUserShouldGetUsername() {
      // Given

      // When
      userAssembler.assembleUser(userSignupDto);

      // Then
      verify(userSignupDto).getUsername();
   }

   @Test
   public void givenValidUserSignupDtoWhenAssembleUserShouldGetPhoneNumber() {
      // Given

      // When
      userAssembler.assembleUser(userSignupDto);

      // Then
      verify(userSignupDto).getPhoneNumber();
   }

   @Test
   public void givenValidUserSignupDtoWhenAssembleUserShouldGetPassword() {
      // Given

      // When
      userAssembler.assembleUser(userSignupDto);

      // Then
      verify(userSignupDto).getPassword();
   }

   @Test
   public void givenValidUserSignupDtoWhenAssembleUserShouldGetRole() {
      // Given

      // When
      userAssembler.assembleUser(userSignupDto);

      // Then
      verify(userSignupDto).getRole();
   }

   @Test
   public void givenValidUserSignupDtoWhenAssemblerUserShouldReturnValidUserFirstName() {
      // Given

      // When
      User user = userAssembler.assembleUser(userSignupDto);

      // Then
      assertEquals(FIRST_NAME, user.getContactInformation().getFirstName());
   }

   @Test
   public void givenValidUserSignupDtoWhenAssemblerUserShouldReturnValidUserLasttName() {
      // Given

      // When
      User user = userAssembler.assembleUser(userSignupDto);

      // Then
      assertEquals(LAST_NAME, user.getContactInformation().getLastName());
   }

   @Test
   public void givenValidUserSignupDtoWhenAssemblerUserShouldReturnValidUserEmail() {
      // Given

      // When
      User user = userAssembler.assembleUser(userSignupDto);

      // Then
      assertEquals(EMAIL, user.getContactInformation().getEmail());
   }

   @Test
   public void givenValidUserSignupDtoWhenAssemblerUserShouldReturnValidUserPhoneNumber() {
      // Given

      // When
      User user = userAssembler.assembleUser(userSignupDto);

      // Then
      assertEquals(PHONE_NUMBER, user.getContactInformation().getPhoneNumber());
   }

   @Test
   public void givenValidUserSignupDtoWhenAssemblerUserShouldReturnValidUsername() {
      // Given

      // When
      User user = userAssembler.assembleUser(userSignupDto);

      // Then
      assertEquals(USERNAME, user.getUsername());
   }

   @Test
   public void givenValidUserSignupDtoWhenAssemblerUserShouldReturnValidUserRole() {
      // Given

      // When
      User user = userAssembler.assembleUser(userSignupDto);

      // Then
      assertEquals(ROLE, user.getRole().getRoles());
   }

   @Test
   public void givenValidUserSignupDtoWhenAssemblerUserShouldReturnValidUserPassword() {
      // Given

      // When
      User user = userAssembler.assembleUser(userSignupDto);

      // Then
      assertEquals(PASSWORD, user.getPassword());
   }

   @Test
   public void givenValidUserWhenConvertToUserDetailedDtoUserShouldGetContactInformation() {
      // Given
      configureUserToConvert();
      // When
      userAssembler.convertUserToDetailedDto(userToConvert);

      // Then
      verify(userToConvert, times(4)).getContactInformation();

   }

   @Test
   public void givenValidUserWhenConvertToUserDetailedDtoUserShouldGetFirstName() {
      // Given
      configureUserToConvert();
      // When
      userAssembler.convertUserToDetailedDto(userToConvert);

      // Then
      verify(contactInformation).getFirstName();

   }

   @Test
   public void givenValidUserWhenConvertToUserDetailedDtoUserShouldGetLastName() {
      // Given
      configureUserToConvert();
      // When
      userAssembler.convertUserToDetailedDto(userToConvert);

      // Then
      verify(contactInformation).getLastName();

   }

   @Test
   public void givenValidUserWhenConvertToUserDetailedDtoUserShouldGetEmail() {
      // Given
      configureUserToConvert();
      // When
      userAssembler.convertUserToDetailedDto(userToConvert);

      // Then
      verify(contactInformation).getEmail();
   }

   @Test
   public void givenValidUserWhenConvertToUserDetailedDtoUserShouldGetPhoneNumber() {
      // Given
      configureUserToConvert();
      // When
      userAssembler.convertUserToDetailedDto(userToConvert);
      // Then
      verify(contactInformation).getPhoneNumber();

   }

   @Test
   public void givenValidUserWhenConvertToUserDetailedDtoUserShouldGetUsername() {
      // Given
      configureUserToConvert();
      // When
      userAssembler.convertUserToDetailedDto(userToConvert);
      // Then
      verify(userToConvert).getUsername();

   }

   @Test
   public void givenValidUserWhenConvertToUserDetailedDtoUserShouldReturnUsername() {
      // Given
      configureUserToConvert();
      // When
      UserDetailedDto userDetailedDto = userAssembler.convertUserToDetailedDto(userToConvert);
      // Then
      assertEquals(USERNAME, userDetailedDto.getUsername());
   }

   @Test
   public void givenValidUserWhenConvertToUserDetailedDtoUserShouldReturnPhoneNumber() {
      // Given
      configureUserToConvert();
      // When
      UserDetailedDto userDetailedDto = userAssembler.convertUserToDetailedDto(userToConvert);
      // Then
      assertEquals(PHONE_NUMBER, userDetailedDto.getPhoneNumber());
   }

   @Test
   public void givenValidUserWhenConvertToUserDetailedDtoUserShouldReturnFirstName() {
      // Given
      configureUserToConvert();
      // When
      UserDetailedDto userDetailedDto = userAssembler.convertUserToDetailedDto(userToConvert);
      // Then
      assertEquals(FIRST_NAME, userDetailedDto.getFirstName());
   }

   @Test
   public void givenValidUserWhenConvertToUserDetailedDtoUserShouldReturnLastName() {
      // Given
      configureUserToConvert();
      // When
      UserDetailedDto userDetailedDto = userAssembler.convertUserToDetailedDto(userToConvert);
      // Then
      assertEquals(LAST_NAME, userDetailedDto.getLastName());
   }

   @Test
   public void givenValidUserWhenConvertToUserDetailedDtoUserShouldReturnEmail() {
      // Given
      configureUserToConvert();
      // When
      UserDetailedDto userDetailedDto = userAssembler.convertUserToDetailedDto(userToConvert);
      // Then
      assertEquals(EMAIL, userDetailedDto.getEmail());
   }

   private void configureUserToConvert() {
      given(contactInformation.getFirstName()).willReturn(FIRST_NAME);
      given(contactInformation.getLastName()).willReturn(LAST_NAME);
      given(contactInformation.getEmail()).willReturn(EMAIL);
      given(contactInformation.getPhoneNumber()).willReturn(PHONE_NUMBER);

      given(userToConvert.getContactInformation()).willReturn(contactInformation);

      given(userToConvert.getUsername()).willReturn(USERNAME);

   }

   private void configureValidUserSignupDto() {
      given(userSignupDto.getFirstName()).willReturn(FIRST_NAME);
      given(userSignupDto.getLastName()).willReturn(LAST_NAME);
      given(userSignupDto.getEmail()).willReturn(EMAIL);
      given(userSignupDto.getPhoneNumber()).willReturn(PHONE_NUMBER);
      given(userSignupDto.getUsername()).willReturn(USERNAME);
      given(userSignupDto.getPassword()).willReturn(PASSWORD);
      given(userSignupDto.getRole()).willReturn(ROLE);
   }
}
