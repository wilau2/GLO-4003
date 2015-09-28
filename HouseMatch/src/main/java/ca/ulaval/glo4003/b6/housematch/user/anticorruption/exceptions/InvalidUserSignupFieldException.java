package ca.ulaval.glo4003.b6.housematch.user.anticorruption.exceptions;

public class InvalidUserSignupFieldException extends Exception {

   private static final long serialVersionUID = 1L;

   public InvalidUserSignupFieldException(String message) {
      super(message);
   }
}
