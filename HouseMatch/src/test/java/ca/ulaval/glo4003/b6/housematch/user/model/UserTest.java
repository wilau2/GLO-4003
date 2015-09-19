package ca.ulaval.glo4003.b6.housematch.user.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

   private User user;

   private String username = "username";

   private String firstName = "firsname";

   private String lastName = "lastname";

   private String phoneNumber = "phoneNumber";

   private String email = "email";

   private String password = "password";

   @Before
   public void setup() {
      user = new User(username, firstName, lastName, phoneNumber, email, password);
   }

   @Test
   public void canGetTheCorrectUsername() {
      assertEquals(username, user.getUsername());
   }

   @Test
   public void canGetTheCorrectFirstName() {
      assertEquals(firstName, user.getFirstName());
   }

   @Test
   public void canGetTheCorrectLastName() {
      assertEquals(lastName, user.getLastName());
   }

   @Test
   public void canGetTheCorrectPhoneNumber() {
      assertEquals(phoneNumber, user.getPhoneNumber());
   }

   @Test
   public void canGetTheCorrectEmail() {
      assertEquals(email, user.getEmail());
   }

   @Test
   public void canGetTheCorrectPassword() {
      assertEquals(password, user.getPassword());
   }

}
