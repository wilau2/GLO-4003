package ca.ulaval.glo4003.b6.housematch.anticorruption.estate;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidEstateFieldException;

public class PictureCorruptionVerificatorTest {

   private static final String PICTURE_NAME = "PICTURE_NAME";

   private static final String FILE_NAME = "FILE_NAME.jpg";

   private PictureCorruptionVerificator pictureCorruptionVerificator;

   @Before
   public void setup() {
      pictureCorruptionVerificator = new PictureCorruptionVerificator();
   }

   @Test
   public void validatingPictureWhenPictureIsValidShouldNotThrowException() throws InvalidEstateFieldException {
      // Given no changes

      // When
      pictureCorruptionVerificator.validatePictureValidity(PICTURE_NAME, FILE_NAME);

      // Then no exception is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void validatingPictureWhenPictureNameIsEmptyShouldThrowException() throws InvalidEstateFieldException {
      // Given
      String emptyPictureName = "";

      // When
      pictureCorruptionVerificator.validatePictureValidity(emptyPictureName, FILE_NAME);

      // Then an invalid estate field exception is thrown
   }

   @Test(expected = InvalidEstateFieldException.class)
   public void validatingPictureWhenFileNameIsNotContainingTheGoodExtensionShouldThrowException()
         throws InvalidEstateFieldException {
      // Given
      String fileNameWithBadException = "file_name_without_extension";

      // When
      pictureCorruptionVerificator.validatePictureValidity(PICTURE_NAME, fileNameWithBadException);

      // Then an invalid estate field exception is thrown
   }
}
