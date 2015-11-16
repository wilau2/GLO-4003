package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PictureSelector {

   private final String NO_PHOTO_AVAILABLE_MESSAGE = "You should add some photos!";

   private Album activePictureAlbum;

   private PictureRepository pictureRepository;

   public PictureSelector(Album activePictureAlbum, PictureRepository pictureRepository) {
      this.activePictureAlbum = activePictureAlbum;
      this.pictureRepository = pictureRepository;
   }

   public byte[] getPicture(String pictureName) throws IOException {
      if (pictureName.equals(NO_PHOTO_AVAILABLE_MESSAGE)) {
         return pictureRepository.getEmptyPicture();

      }
      return pictureRepository.getPicture(pictureName, activePictureAlbum.getEstateAddress());
   }

   public void deletePicture(String pictureName) {
      pictureRepository.deletePicture(pictureName, activePictureAlbum.getEstateAddress());
   }

   public void addPicture(String pictureName, MultipartFile file) throws IOException {
      pictureRepository.addPicture(pictureName, activePictureAlbum.getEstateAddress(), file);
   }

   public List<String> getRelevantPictures() {
      List<String> everyAvailablePictures = pictureRepository
            .getEveryPicturesNames(activePictureAlbum.getEstateAddress());
      if (everyAvailablePictures.isEmpty()) {
         everyAvailablePictures.add(NO_PHOTO_AVAILABLE_MESSAGE);
      }
      return everyAvailablePictures;
   }
}
