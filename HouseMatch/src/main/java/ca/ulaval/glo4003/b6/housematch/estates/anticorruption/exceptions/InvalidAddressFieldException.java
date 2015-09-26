package ca.ulaval.glo4003.b6.housematch.estates.anticorruption.exceptions;


public class InvalidAddressFieldException extends Exception{
   
   private static final long serialVersionUID = 1L;

   public InvalidAddressFieldException(String message, Exception exception) {
      super(message, exception);
   }

   public InvalidAddressFieldException(String message) {
      super(message);
   }

}
