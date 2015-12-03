package ca.ulaval.glo4003.b6.housematch.domain.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ContactInformationTest {

   private static final String FIRST_NAME = "firstname";

   private static final String LAST_NAME = "lastname";

   private static final String PHONE_NUMBER = "phoneNumber";

   private static final String EMAIL = "email";

   private static final String NEW_FIRST_NAME = "new firsname";

   private static final String NEW_LAST_NAME = "new lastname";

   private static final String NEW_PHONE_NUMBER = "new phoneNumber";

   private static final String NEW_EMAIL = "new email";

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

   @Test
   public void givenNewEmailWhenIsEmailChangedShouldReturnTrue() {
      // Given
      ContactInformation newContactInformation = new ContactInformation(NEW_FIRST_NAME, NEW_LAST_NAME, NEW_PHONE_NUMBER,
            NEW_EMAIL);
      // When
      boolean isEmailChanged = contactInformation.isEmailChanged(newContactInformation);

      // Then
      assertTrue(isEmailChanged);
   }

   @Test
   public void givenOldEmailWhenIsEmailChangedShouldReturnFalse() {
      // Given
      ContactInformation newContactInformation = new ContactInformation(NEW_FIRST_NAME, NEW_LAST_NAME, NEW_PHONE_NUMBER,
            contactInformation.getEmail());

      // When
      boolean isEmailChanged = contactInformation.isEmailChanged(newContactInformation);

      // Then
      assertFalse(isEmailChanged);
   }

}