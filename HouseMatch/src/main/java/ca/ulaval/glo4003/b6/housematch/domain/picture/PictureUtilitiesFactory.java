package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.ArrayList;
import java.util.List;

public class PictureUtilitiesFactory {

   public Album createAlbum(List<String> activePictureNames, String estateAddress) {
      return new Album(estateAddress, activePictureNames);

   }

   public Album createAlbum(String estateAddress) {
      return new Album(estateAddress, new ArrayList<String>());

   }

   public PictureSelector createPictureSelector(Album album, PictureRepository pictureRepository) {
      return new PictureSelector(album, pictureRepository);
   }
}
