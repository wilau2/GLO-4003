package ca.ulaval.glo4003.b6.housematch.user.dto.validators;

public class UserValidatorFactory {

   public UserValidator getValidator() {
      return new UserValidator();
   }

}
