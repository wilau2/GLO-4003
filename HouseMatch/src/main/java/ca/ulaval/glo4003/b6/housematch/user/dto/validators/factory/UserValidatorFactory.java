package ca.ulaval.glo4003.b6.housematch.user.dto.validators.factory;

import ca.ulaval.glo4003.b6.housematch.user.dto.validators.UserValidator;

public class UserValidatorFactory {

   public UserValidator getValidator() {
      return new UserValidator();
   }

}
