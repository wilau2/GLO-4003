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
      user = new User(USERNAME, FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL, PASSWORD);
   }

   @Test
   public void canGetTheCorrectUsername() {
      assertEquals(USERNAME, user.getUsername());
   }

   @Test
   public void canGetTheCorrectFirstName() {
      assertEquals(FIRST_NAME, user.getFirstName());
   }

   @Test
   public void canGetTheCorrectLastName() {
      assertEquals(LAST_NAME, user.getLastName());
   }

   @Test
   public void canGetTheCorrectPhoneNumber() {
      assertEquals(PHONE_NUMBER, user.getPhoneNumber());
   }

   @Test
   public void canGetTheCorrectEmail() {
      assertEquals(EMAIL, user.getEmail());
   }

   @Test
   public void canGetTheCorrectPassword() {
      assertEquals(PASSWORD, user.getPassword());
   }

}
