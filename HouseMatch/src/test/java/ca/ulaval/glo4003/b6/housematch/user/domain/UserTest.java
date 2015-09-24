package ca.ulaval.glo4003.b6.housematch.user.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

   private User user;

   private static String USERNAME = "username";

   private static String FIRST_NAME = "firsname";

   private static String LAST_NAME = "lastname";

   private static String PHONE_NUMBER = "phoneNumber";

   private static String EMAIL = "email";

   private static String PASSWORD = "password";

   @Before
   public void setup() {
      ContactInformation contactInformation = new ContactInformation(FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL);
      Role role = new Role("SELLER");
      user = new User(USERNAME, PASSWORD, contactInformation, role);
   }

   @Test
   public void canGetTheCorrectUsername() {
      assertEquals(USERNAME, user.getUsername());
   }

   @Test
   public void canGetTheCorrectFirstName() {
      assertEquals(FIRST_NAME, user.getContactInformation().getFirstName());
   }

   @Test
   public void canGetTheCorrectLastName() {
      assertEquals(LAST_NAME, user.getContactInformation().getLastName());
   }

   @Test
   public void canGetTheCorrectPhoneNumber() {
      assertEquals(PHONE_NUMBER, user.getContactInformation().getPhoneNumber());
   }

   @Test
   public void canGetTheCorrectEmail() {
      assertEquals(EMAIL, user.getContactInformation().getEmail());
   }

   @Test
   public void canGetTheCorrectPassword() {
      assertEquals(PASSWORD, user.getPassword());
   }

}
