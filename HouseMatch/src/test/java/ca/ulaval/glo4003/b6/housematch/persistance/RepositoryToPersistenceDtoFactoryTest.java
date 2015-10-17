package ca.ulaval.glo4003.b6.housematch.persistance;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.domain.user.Role;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.persistance.user.RepositoryToPersistenceUserDto;

public class RepositoryToPersistenceDtoFactoryTest {

   @Mock
   private User user;

   @Mock
   private ContactInformation userInfos;

   @Mock
   private Role role;

   private RepositoryToPersistenceDtoFactory factory;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      factory = new RepositoryToPersistenceDtoFactory();

      configureUser();

   }

   @Test
   public void shouldReturnAUserDtoIfGivenAUser() {
      // Given

      // When
      RepositoryToPersistenceDto dto = factory.getRepositoryDto(user);

      // Then
      assertTrue(dto instanceof RepositoryToPersistenceUserDto);
   }

   private void configureUser() {
      given(user.getUsername()).willReturn("username");
      given(user.getPassword()).willReturn("password");

      given(user.getRole()).willReturn(role);
      given(role.getRoles()).willReturn("roles");

      given(user.getContactInformation()).willReturn(userInfos);
      given(userInfos.getFirstName()).willReturn("firstName");
      given(userInfos.getLastName()).willReturn("lastname");
      given(userInfos.getPhoneNumber()).willReturn("phonenumber");
      given(userInfos.getEmail()).willReturn("email");
   }

}
