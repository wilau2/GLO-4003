package ca.ulaval.glo4003.b6.housematch.user.dto;

import org.junit.Before;
import org.junit.Test;

public class CustomUserDtoTest {

   private CustomUserDto userDto;

   @Before
   public void setup() {

   }

   @Test
   public void shouldBeAbleToCreateAUserWith() {
      // Given

      // When
      userDto = new CustomUserDto.UserDtoBuilder("username").build();
      CustomUserDto userDto2 = new CustomUserDto.UserDtoBuilder("username").email("email").build();

      // Then
      String username = userDto2.password;
      System.out.println(username);
   }

}
