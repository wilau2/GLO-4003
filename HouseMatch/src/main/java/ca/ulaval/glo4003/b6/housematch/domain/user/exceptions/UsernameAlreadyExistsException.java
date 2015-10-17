package ca.ulaval.glo4003.b6.housematch.domain.user.exceptions;

public class UsernameAlreadyExistsException extends Exception {

   private static final long serialVersionUID = 1L;

   public UsernameAlreadyExistsException(String message) {
      super(message);
   }

}
