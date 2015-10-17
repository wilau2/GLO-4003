package ca.ulaval.glo4003.b6.housematch.services.user.exceptions;

public class InvalidPasswordException extends Exception {

   private static final long serialVersionUID = 1L;

   public InvalidPasswordException(String message) {
      super(message);
   }

}
