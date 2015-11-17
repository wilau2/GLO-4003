package ca.ulaval.glo4003.b6.housematch.anticorruption.estate;

import org.apache.commons.io.FilenameUtils;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidEstateFieldException;

public class PictureCorruptionVerificator {

   public void validatePictureValidity(String pictureName, String fileName) throws InvalidEstateFieldException {
      if (pictureName == null || pictureName.isEmpty()) {
         throw new InvalidEstateFieldException("The selected picture name is empty");
      }
      if (!FilenameUtils.isExtension(fileName, "jpg")) {
         throw new InvalidEstateFieldException("The picture must be a .jpg file");
      }
   }
}
