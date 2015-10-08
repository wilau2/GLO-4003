package ca.ulaval.glo4003.b6.housematch.user.domain.assembler;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.dto.ContactInformationDto;
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
   private ContactInformationAssembler contactInformationAssembler;

   @Mock
   private UserSignupDto userSignupDto;

   @Mock
   private User userToConvert;

   @Mock
   private ContactInformation contactInformation;

   @Mock
   private Role role;

   @Mock
   private ContactInformationDto contactInformationDto;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureValidUserSignupDto();
   }

   @Test
   public void givenValidUserSignupDtoWhenAssembleUserShouldGetContactInformation() {
      // Given

      // When
      userAssembler.assembleUser(userSignupDto);

      // Then
      verify(userSignupDto).getContactInformationDto();
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
   public void givenValidUserSignupDtoWhenAssembleUserShouldDelagateContactInformationAssembling() {
      // Given

      // When
      userAssembler.assembleUser(userSignupDto);
      // Then
      verify(contactInformationAssembler).assembleContactInformation(contactInformationDto);
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

   private void configureValidUserSignupDto() {
      given(userSignupDto.getContactInformationDto()).willReturn(contactInformationDto);
      given(contactInformationDto.getFirstName()).willReturn(FIRST_NAME);
      given(contactInformationDto.getLastName()).willReturn(LAST_NAME);
      given(contactInformationDto.getEmail()).willReturn(EMAIL);
      given(contactInformationDto.getPhoneNumber()).willReturn(PHONE_NUMBER);
      given(userSignupDto.getUsername()).willReturn(USERNAME);
      given(userSignupDto.getPassword()).willReturn(PASSWORD);
      given(userSignupDto.getRole()).willReturn(ROLE);
   }
}
