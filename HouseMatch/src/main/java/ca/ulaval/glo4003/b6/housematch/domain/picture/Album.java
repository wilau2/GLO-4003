package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class Album {

   private String estateAddress;

   private final String NO_PHOTO_AVAILABLE_MESSAGE = "You should add some photos!";

   private List<String> activePictureNames;

   Album(String estateAddress, List<String> activePictureNames) {
      this.estateAddress = estateAddress;
      this.activePictureNames = activePictureNames;
   }

   public byte[] getSpecificPicture(String pictureName, PictureRepository pictureRepository)
         throws CouldNotAccessDataException {
      if (pictureName.equals(NO_PHOTO_AVAILABLE_MESSAGE)) {
         return pictureRepository.getEmptyPicture();
      }
      return pictureRepository.getPicture(pictureName, estateAddress);
   }

   public List<String> getRelevantPictures() {
      List<String> everyAvailablePictures = activePictureNames;

      if (everyAvailablePictures.isEmpty()) {
         everyAvailablePictures.add(NO_PHOTO_AVAILABLE_MESSAGE);
      }
      return everyAvailablePictures;
   }

}
