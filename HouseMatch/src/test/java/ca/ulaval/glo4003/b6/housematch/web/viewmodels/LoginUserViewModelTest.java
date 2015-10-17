package ca.ulaval.glo4003.b6.housematch.web.viewmodels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.web.viewmodels.LoginUserViewModel;

public class LoginUserViewModelTest {

   private LoginUserViewModel viewUser;

   @Before
   public void setup() {
      viewUser = new LoginUserViewModel();
   }

   @Test
   public void newUserHasNoUsername() {
      assertNull(viewUser.getUsername());
   }

   @Test
   public void newUserHasNoPassword() {
      assertNull(viewUser.getPassword());
   }

   @Test
   public void usernameSetterAndGetter() {
      String testEmail = "anEmail";

      viewUser.setUsername(testEmail);

      assertEquals(testEmail, viewUser.getUsername());
   }

   @Test
   public void passwordSetterAndGetter() {
      String testPassword = "a password";

      viewUser.setPassword(testPassword);

      assertEquals(testPassword, viewUser.getPassword());
   }
}
