package ca.ulaval.glo4003.b6.housematch.persistence.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;

public class UserPersistenceDtoTest {

   @Mock
   private User user;

   @Mock
   private ContactInformation userInfos;

   @Mock
   private Role role;

   private UserPersistenceDto dto;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      configureUser();
      dto = new UserPersistenceDto(user);

   }

   @Test
   public void shouldReturnTheCorrespondingHasMap() {
      // Given
      HashMap<String, String> returnedMap;

      // When
      returnedMap = dto.getAttributes();

      // Then
      assertEquals(correspondingHashMap(), returnedMap);
   }

   @Test
   public void shouldReturnTheCorrespondingElementName() {
      // Given
      String elementName;

      // When
      elementName = dto.getElementName();

      // Then
      assertEquals("user", elementName);
   }

   private void configureUser() {
      given(user.getUsername()).willReturn("username");
      given(user.getPassword()).willReturn("password");
      given(user.isActive()).willReturn(true);

      given(user.getRole()).willReturn(role);
      given(role.getRoles()).willReturn("roles");

      given(user.getContactInformation()).willReturn(userInfos);
      given(userInfos.getFirstName()).willReturn("firstName");
      given(userInfos.getLastName()).willReturn("lastName");
      given(userInfos.getPhoneNumber()).willReturn("phonenumber");
      given(userInfos.getEmail()).willReturn("email");
   }

   private HashMap<String, String> correspondingHashMap() {

      HashMap<String, String> attributes = new HashMap<String, String>();
      attributes.put("role", "roles");
      attributes.put("username", "username");
      attributes.put("firstName", "firstName");
      attributes.put("lastName", "lastName");
      attributes.put("phoneNumber", "phonenumber");
      attributes.put("email", "email");
      attributes.put("password", "password");
      attributes.put("isActive", "true");

      return attributes;
   }

}
