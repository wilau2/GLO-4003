package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PictureSelector {

   private final String NO_PHOTO_AVAILABLE_MESSAGE = "You should add some photos!";

   private String estateAddress;

   private PictureRepository pictureRepository;

   public PictureSelector(String estateAddress, PictureRepository pictureRepository) {
      this.estateAddress = estateAddress;
      this.pictureRepository = pictureRepository;
   }

   public byte[] getPicture(String pictureName) throws IOException {
      if (pictureName.equals(NO_PHOTO_AVAILABLE_MESSAGE)) {
         return pictureRepository.getDefaultPicture();

      }
      return pictureRepository.getPicture(pictureName, estateAddress);
   }

   public void deletePicture(String pictureName) {
      pictureRepository.deletePicture(pictureName, estateAddress);
   }

   public void addPicture(String pictureName, MultipartFile file) throws IOException {
      pictureRepository.addPicture(pictureName, estateAddress, file);
   }

   public List<String> getRelevantPicturesUrl() {
      List<String> everyAvailablePictures = pictureRepository.getEveryPicturesNames(estateAddress);
      if (everyAvailablePictures.isEmpty()) {
         everyAvailablePictures.add(NO_PHOTO_AVAILABLE_MESSAGE);
      }
      return everyAvailablePictures;
   }
}
