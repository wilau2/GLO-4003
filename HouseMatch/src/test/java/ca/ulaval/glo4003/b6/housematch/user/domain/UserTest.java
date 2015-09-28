package ca.ulaval.glo4003.b6.housematch.user.domain;

import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserTest {

   @Mock
   private ContactInformation contactInformation;

   private Role role;

   @InjectMocks
   private User user;

   private static String USERNAME = "username";

   private static String PASSWORD = "password";

}
