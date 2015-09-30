package ca.ulaval.glo4003.b6.housematch.user.services.exceptions;

public class InvalidPasswordException extends Exception {

   private static final long serialVersionUID = 1L;

   public InvalidPasswordException(String message) {
      super(message);
   }

}
