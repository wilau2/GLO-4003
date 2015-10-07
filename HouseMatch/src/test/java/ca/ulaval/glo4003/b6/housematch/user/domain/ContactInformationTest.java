package ca.ulaval.glo4003.b6.housematch.user.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ContactInformationTest {

   private final static String FIRST_NAME = "firsname";

   private final static String LAST_NAME = "lastname";

   private final static String PHONE_NUMBER = "phoneNumber";

   private final static String EMAIL = "email";

   private final static String NEW_FIRST_NAME = "new firsname";

   private final static String NEW_LAST_NAME = "new lastname";

   private final static String NEW_PHONE_NUMBER = "new phoneNumber";

   private final static String NEW_EMAIL = "new email";

   private ContactInformation contactInformation;

   @Before
   public void setup() {
      contactInformation = new ContactInformation(FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL);
   }

   @Test
   public void canGetTheCorrectFirstName() {
      assertEquals(FIRST_NAME, contactInformation.getFirstName());
   }

   @Test
   public void canGetTheCorrectLastName() {
      assertEquals(LAST_NAME, contactInformation.getLastName());
   }

   @Test
   public void canGetTheCorrectPhoneNumber() {
      assertEquals(PHONE_NUMBER, contactInformation.getPhoneNumber());
   }

   @Test
   public void canGetTheCorrectEmail() {
      assertEquals(EMAIL, contactInformation.getEmail());
   }

   @Test
   public void givenNewContactInformationWhenUpdateThenFirstNameIsUpdated() {
      // Given
      ContactInformation newContactInformation = new ContactInformation(NEW_FIRST_NAME, NEW_LAST_NAME, NEW_PHONE_NUMBER,
            NEW_EMAIL);

      // When
      contactInformation.update(newContactInformation);

      // Then
      assertEquals(NEW_FIRST_NAME, contactInformation.getFirstName());
   }

   @Test
   public void givenNewContactInformationWhenUpdateThenLastNameIsUpdated() {
      // Given
      ContactInformation newContactInformation = new ContactInformation(NEW_FIRST_NAME, NEW_LAST_NAME, NEW_PHONE_NUMBER,
            NEW_EMAIL);

      // When
      contactInformation.update(newContactInformation);

      // Then
      assertEquals(NEW_LAST_NAME, contactInformation.getLastName());
   }

   @Test
   public void givenNewContactInformationWhenUpdateThenPhoneNumberIsUpdated() {
      // Given
      ContactInformation newContactInformation = new ContactInformation(NEW_FIRST_NAME, NEW_LAST_NAME, NEW_PHONE_NUMBER,
            NEW_EMAIL);

      // When
      contactInformation.update(newContactInformation);

      // Then
      assertEquals(NEW_PHONE_NUMBER, contactInformation.getPhoneNumber());
   }

   @Test
   public void givenNewContactInformationWhenUpdateThenEmailIsUpdated() {
      // Given
      ContactInformation newContactInformation = new ContactInformation(NEW_FIRST_NAME, NEW_LAST_NAME, NEW_PHONE_NUMBER,
            NEW_EMAIL);

      // When
      contactInformation.update(newContactInformation);

      // Then
      assertEquals(NEW_EMAIL, contactInformation.getEmail());
   }
}
