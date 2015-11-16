package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PicturesSelector {

   private final String NO_PHOTO_AVAILABLE_MESSAGE = "You should add some photos!";

   private String estateAddress;

   public PicturesSelector(String estateAddress) {
      this.estateAddress = estateAddress;
   }

   public byte[] getPicture(String pictureName, PhotoAlbum album) throws IOException {
      if (pictureName.equals(NO_PHOTO_AVAILABLE_MESSAGE)) {
         return album.getDefaultPicture();
      }
      return album.getPicture(pictureName, estateAddress);
   }

   public void deletePicture(String pictureName, PhotoAlbum album) {
      album.deletePicture(pictureName, estateAddress);
   }

   public void addPicture(String pictureName, MultipartFile file, PhotoAlbum album) throws IOException {
      album.addPicture(pictureName, estateAddress, file);
   }

   public List<String> getRelevantPicturesUrl(PhotoAlbum album) {
      List<String> everyAvailablePictures = album.getEveryPicturesNames(estateAddress);
      if (everyAvailablePictures.isEmpty()) {
         everyAvailablePictures.add(NO_PHOTO_AVAILABLE_MESSAGE);
      }
      return everyAvailablePictures;
   }
}
