package ca.ulaval.glo4003.b6.housematch.anticorruption.user.exceptions;

public class InvalidUserSignupFieldException extends Exception {

   private static final long serialVersionUID = 1L;

   public InvalidUserSignupFieldException(String message) {
      super(message);
   }
}
