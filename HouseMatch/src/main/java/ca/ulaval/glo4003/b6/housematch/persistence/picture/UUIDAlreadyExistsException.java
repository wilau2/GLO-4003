package ca.ulaval.glo4003.b6.housematch.persistence.picture;

public class UUIDAlreadyExistsException extends Exception {

   private static final long serialVersionUID = 1L;

   public UUIDAlreadyExistsException(String message) {
      super(message);
   }

}
