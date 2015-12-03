package ca.ulaval.glo4003.b6.housematch.domain.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserTest {

   private static final String USERNAME = "username";

   private static final String PASSWORD = "password";

   private static final String INVALID_PASSWORD = "invalid_password";

   @Mock
   private ContactInformation contactInformation;

   @Mock
   private Role role;

   @Mock
   private ContactInformation contactInformationForUpdate;

   @InjectMocks
   private User user;

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

   @Test
   public void whenAskingIfUserWasActiveInLastSixMonthsShouldReturnFalseIfUserNotActive() {
      // Given
      user.setActive(false);

      // When
      boolean activeInTheLastSixMonths = user.wasActiveInTheLastSixMonths();

      // Then
      assertFalse(activeInTheLastSixMonths);
   }

   @Test
   public void askingIfUserWasActiveInLastSixMonthsWhenUserWasNotActiveInLastSixMonthsShouldReturnFalse() {
      // Given
      user.setActive(true);
      LocalDateTime dateBeforeSixMonthsAgo = LocalDateTime.now().minusMonths(6).minusSeconds(1);
      user.setDateOfLastActivity(dateBeforeSixMonthsAgo);

      // When
      boolean wasActiveInTheLastSixMonths = user.wasActiveInTheLastSixMonths();

      // Then
      assertFalse(wasActiveInTheLastSixMonths);
   }

   @Test
   public void askingIfUserWasActiveInLastSixMonthsWhenUserWasActiveShouldReturnTrue() {
      // Given
      user.setActive(true);
      user.setDateOfLastActivity(LocalDateTime.now());

      // When
      boolean wasActiveInTheLastSixMonths = user.wasActiveInTheLastSixMonths();

      // Then
      assertTrue(wasActiveInTheLastSixMonths);
   }

   @Test
   public void whenUpdateUserLastActivityShouldSetDateToCurrentDate() {
      // Given no changes

      // When
      user.updateLastActivity();
      LocalDateTime dateOfLastActivity = user.getDateOfLastActivity();

      // Then
      assertEquals(LocalDateTime.now().getDayOfMonth(), dateOfLastActivity.getDayOfMonth());
   }

   @Test
   public void whenValidatingUserCredentialShouldReturnTrueIsPasswordIsEqual() {
      // Given no changes
      configureUser();

      // When
      boolean loginCredential = user.validateLoginCredential(PASSWORD);

      // Then
      assertTrue(loginCredential);
   }

   @Test
   public void validatingUserCredentialWhenPasswordToVerifyIsNotTheSameShouldReturnFalse() {
      // Given
      configureUser();

      // When
      boolean loginCredential = user.validateLoginCredential(INVALID_PASSWORD);

      // Then
      assertFalse(loginCredential);
   }

   @Test
   public void whenUpdatingContactInformationShouldCallUpdateMethodOnContactInformation() {
      // Given
      configureUser();

      // When
      user.updateContactInformation(contactInformationForUpdate);

      // Then
      verify(contactInformation).update(contactInformationForUpdate);
   }

   @Test
   public void givenNewEmailWhenUpdateContactInformationShouldSetActiveToFalse() {
      // Given
      configureUser();
      user.setActive(true);
      when(contactInformation.isEmailChanged(contactInformationForUpdate)).thenReturn(true);

      // When

      user.updateContactInformation(contactInformationForUpdate);

      // Then
      assertEquals(false, user.isActive());
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
