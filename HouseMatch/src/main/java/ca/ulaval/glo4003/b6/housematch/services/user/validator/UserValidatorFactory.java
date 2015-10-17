package ca.ulaval.glo4003.b6.housematch.services.user.validator;

public class UserValidatorFactory {

   public UserValidator getValidator() {
      return new UserValidator();
   }

}
