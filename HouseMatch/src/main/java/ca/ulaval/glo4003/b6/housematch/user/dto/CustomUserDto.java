package ca.ulaval.glo4003.b6.housematch.user.dto;

// Builder Pattern
public class CustomUserDto {

   public final String username;

   public final String firstName;

   public final String lastName;

   public final String phoneNumber;

   public final String email;

   public final String password;

   public final String role;

   public static class UserDtoBuilder {

      private final String username;

      private String firstName = "default Value";

      private String lastName = "default Value";

      private String phoneNumber = "default Value";

      private String email = "default Value";

      private String password = "default Value";

      private String role = "default Value";

      public UserDtoBuilder(String username) {
         this.username = username;
      }

      public UserDtoBuilder firstName(String value) {
         firstName = value;
         return this;
      }

      public UserDtoBuilder lastName(String value) {
         lastName = value;
         return this;
      }

      public UserDtoBuilder phoneNumber(String value) {
         phoneNumber = value;
         return this;
      }

      public UserDtoBuilder email(String value) {
         email = value;
         return this;
      }

      public UserDtoBuilder password(String value) {
         password = value;
         return this;
      }

      public UserDtoBuilder role(String value) {
         role = value;
         return this;
      }

      public CustomUserDto build() {
         return new CustomUserDto(this);
      }
   }

   private CustomUserDto(UserDtoBuilder builder) {
      username = builder.username;
      firstName = builder.firstName;
      lastName = builder.lastName;
      phoneNumber = builder.phoneNumber;
      email = builder.email;
      password = builder.password;
      role = builder.role;
   }
}
