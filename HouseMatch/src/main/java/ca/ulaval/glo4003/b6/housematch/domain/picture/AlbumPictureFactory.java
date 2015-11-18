package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.ArrayList;
import java.util.List;

public class AlbumPictureFactory {

   public Album createAlbum(List<String> activePictureNames, String estateAddress) {
      return new Album(estateAddress, activePictureNames);

   }

   public Album createAlbum(String estateAddress) {
      return new Album(estateAddress, new ArrayList<String>());

   }
}
