package ca.ulaval.glo4003.b6.housematch.user.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserTest {

   @Mock
   private ContactInformation contactInformation;

   @Mock
   private Role role;

   @InjectMocks
   private User user;

   private static String USERNAME = "username";

   private static String PASSWORD = "password";

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void canGetTheCorrectUsername() {
      // Given
      configureUser();
      // When
      assertEquals(USERNAME, user.getUsername());
   }

   @Test
   public void canGetTheCorrectPassword() {
      // Given
      configureUser();
      // When
      assertEquals(PASSWORD, user.getPassword());
   }

   @Test
   public void canGetTheCorrectContactInformation() {
      assertEquals(contactInformation, user.getContactInformation());
   }

   @Test
   public void canGetTheCorrectRole() {
      assertEquals(role, user.getRole());
   }

   @Test
   public void givenAdminUserWhenIsUserShouldReturnTrue() {
      // Given
      configureAdminUser();

      // When

      // Then
      assertTrue(user.isAdmin());
   }

   @Test
   public void givenBuyerUserWhenIsUserShouldReturnTrue() {
      // Given
      configureBuyerUser();

      // When

      // Then
      assertTrue(user.isBuyer());
   }

   @Test
   public void givenSellerUserWhenIsUserShouldReturnTrue() {
      // Given
      configureSellerUser();

      // When
      user.isSeller();

      // Then
      assertTrue(user.isSeller());
   }

   private void configureAdminRole() {
      given(role.asAdmin()).willReturn(true);
   }

   private void configureSellerRole() {
      given(role.asSeller()).willReturn(true);
   }

   private void configureBuyerRole() {
      given(role.asBuyer()).willReturn(true);
   }

   private void configureUser() {
      user = new User(USERNAME, PASSWORD, contactInformation, role);
   }

   private void configureAdminUser() {
      configureAdminRole();
      configureUser();
   }

   private void configureSellerUser() {
      configureSellerRole();
      configureUser();
   }

   private void configureBuyerUser() {
      configureBuyerRole();
      configureUser();
   }

}
