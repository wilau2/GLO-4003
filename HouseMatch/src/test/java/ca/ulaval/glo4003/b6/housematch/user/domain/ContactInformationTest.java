package ca.ulaval.glo4003.b6.housematch.user.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ContactInformationTest {

   private static String FIRST_NAME = "firsname";

   private static String LAST_NAME = "lastname";

   private static String PHONE_NUMBER = "phoneNumber";

   private static String EMAIL = "email";

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
}
