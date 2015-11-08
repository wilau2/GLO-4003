package ca.ulaval.glo4003.b6.housematch.persistance;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.persistance.user.UserPersistenceDto;

public class PersistenceDtoFactoryTest {

   @Mock
   private User user;

   @Mock
   private ContactInformation userInfos;

   @Mock
   private Role role;

   private PersistenceDtoFactory factory;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      factory = new PersistenceDtoFactory();

      configureUser();

   }

   @Test
   public void shouldReturnAUserDtoIfGivenAUser() {
      // Given

      // When
      PersistenceDto dto = factory.getRepositoryDto(user);

      // Then
      assertTrue(dto instanceof UserPersistenceDto);
   }

   private void configureUser() {
      given(user.getUsername()).willReturn("username");
      given(user.getPassword()).willReturn("password");
      given(user.getDateOfLastActivity()).willReturn(LocalDateTime.now());

      given(user.getRole()).willReturn(role);
      given(role.getRoles()).willReturn("roles");

      given(user.getContactInformation()).willReturn(userInfos);
      given(userInfos.getFirstName()).willReturn("firstName");
      given(userInfos.getLastName()).willReturn("lastname");
      given(userInfos.getPhoneNumber()).willReturn("phonenumber");
      given(userInfos.getEmail()).willReturn("email");
   }

}
