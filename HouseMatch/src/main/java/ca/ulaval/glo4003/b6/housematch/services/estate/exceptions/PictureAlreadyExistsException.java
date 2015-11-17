package ca.ulaval.glo4003.b6.housematch.services.estate.exceptions;

public class PictureAlreadyExistsException extends Exception {

   private static final long serialVersionUID = 1L;

   public PictureAlreadyExistsException(String message) {
      super(message);
   }
}
