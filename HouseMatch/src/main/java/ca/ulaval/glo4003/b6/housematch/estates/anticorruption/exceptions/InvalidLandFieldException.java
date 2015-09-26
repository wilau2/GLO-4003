package ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions;


public class InvalidLandFieldException extends Exception {
   
   private static final long serialVersionUID = 1L;

   public InvalidLandFieldException(String message, Exception exception) {
      super(message, exception);
   }

   public InvalidLandFieldException(String message) {
      super(message);
   }

}
