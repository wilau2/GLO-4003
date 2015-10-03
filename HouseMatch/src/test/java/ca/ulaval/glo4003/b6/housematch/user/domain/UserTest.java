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

   private final static String USERNAME = "username";

   private final static String PASSWORD = "password";

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   public void shouldReturnTheUsernameGivenAtInitialisation() {
      // Given
      configureUser();

      // When

      // Then
      assertEquals(USERNAME, user.getUsername());
   }

   @Test
   public void shouldReturnThePasswordGivenAtInitialisation() {
      // Given
      configureUser();

      // When

      // Then
      assertEquals(PASSWORD, user.getPassword());
   }

   @Test
   public void shouldReturnTheContactInformationGivenAtInitialisation() {
      // Given
      configureUser();

      // When

      // Then
      assertEquals(contactInformation, user.getContactInformation());
   }

   @Test
   public void shouldReturnTheRoleGivenAtInitialisation() {
      // Given
      configureUser();

      // When

      // Then
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
      given(role.hasAdmin()).willReturn(true);
   }

   private void configureSellerRole() {
      given(role.hasSeller()).willReturn(true);
   }

   private void configureBuyerRole() {
      given(role.hasBuyer()).willReturn(true);
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
