package ca.ulaval.glo4003.b6.housematch.user.repository.exception;

public class UserNotFoundException extends Exception {

   private static final long serialVersionUID = 1L;

   public UserNotFoundException(String message) {
      super(message);
   }
}
