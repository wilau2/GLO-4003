package ca.ulaval.glo4003.b6.housematch.estates.exceptions;


public class InvalidDescriptionException extends Exception {

   private static final long serialVersionUID = 1L;

   public InvalidDescriptionException(String message) {
      super(message);
   }

   public InvalidDescriptionException() {

   }
}
