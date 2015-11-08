package ca.ulaval.glo4003.b6.housematch.persistance.user.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.when;

import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;

public class RepositoryUserConverterTest {

   private static final String EMAIL = "EMAIL";

   private static final String BUYER = "buyer";

   private static final String USERNAME = "USERNAME";

   private static final String PASSWORD = "";

   private static final String LAST_NAME = "LAST_NAME";

   private static final String FIRST_NAME = "FIRST_NAME";

   private static final String PHONE_NUMBER = "PHONE_NUMBER";

   private RepositoryUserConverter repositoryUserConverter;

   @Mock
   private Element element;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      repositoryUserConverter = new RepositoryUserConverter();

      configureElement();
   }

   private void configureElement() {
      when(element.elementText("username")).thenReturn(USERNAME);
      when(element.elementText("role")).thenReturn(BUYER);

      when(element.elementText("email")).thenReturn(EMAIL);
      when(element.elementText("lastName")).thenReturn(LAST_NAME);
      when(element.elementText("firstName")).thenReturn(FIRST_NAME);
      when(element.elementText("phoneNumber")).thenReturn(PHONE_NUMBER);
   }

   @Test
   public void whenAssemblingAUserFromElementShouldSetAllFieldOfAUserCorrectly() {
      // Given no changes

      // When
      User returnedUser = repositoryUserConverter.assembleUserFromElement(element);

      // Then
      assertEquals(USERNAME, returnedUser.getUsername());
      assertEquals(PASSWORD, returnedUser.getPassword());
      assertTrue(returnedUser.isBuyer());
   }

   @Test
   public void whenAssemblingAUserFromElementShouldSetAllFieldsOfContactInformation() {
      // Given no changes

      // When
      User userFromElement = repositoryUserConverter.assembleUserFromElement(element);
      ContactInformation contactInformation = userFromElement.getContactInformation();

      // Then
      assertEquals(EMAIL, contactInformation.getEmail());
      assertEquals(LAST_NAME, contactInformation.getLastName());
      assertEquals(FIRST_NAME, contactInformation.getFirstName());
      assertEquals(PHONE_NUMBER, contactInformation.getPhoneNumber());
   }
}
