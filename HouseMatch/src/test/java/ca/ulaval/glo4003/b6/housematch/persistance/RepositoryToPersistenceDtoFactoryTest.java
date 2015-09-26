package ca.ulaval.glo4003.b6.housematch.persistance;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.repository.RepositoryToPersistenceEstateDto;
import ca.ulaval.glo4003.b6.housematch.user.domain.ContactInformation;
import ca.ulaval.glo4003.b6.housematch.user.domain.Role;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.RepositoryToPersistenceUserDto;

public class RepositoryToPersistenceDtoFactoryTest {

   @Mock
   private User user;

   @Mock
   private ContactInformation userInfos;

   @Mock
   private Role role;

   @Mock
   private Estate estate;

   private RepositoryToPersistenceDtoFactory factory;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);
      factory = new RepositoryToPersistenceDtoFactory();

      configureUser();
      configureEstate();
   }

   @Test
   public void shouldReturnAUserDtoIfGivenAUser() {
      // Given

      // When
      RepositoryToPersistenceDto dto = factory.getRepositoryDto(user);

      // Then
      assertTrue(dto instanceof RepositoryToPersistenceUserDto);
   }

   @Test
   public void shouldReturnAEstateDtoIfGivenAnEstate() {
      // Given

      // When
      RepositoryToPersistenceDto dto = factory.getRepositoryDto(estate);

      // Then
      assertTrue(dto instanceof RepositoryToPersistenceEstateDto);
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

   private void configureEstate() {
      given(estate.getType()).willReturn("type");
      given(estate.getAddress()).willReturn("address");
      given(estate.getPrice()).willReturn(10);
   }

}
