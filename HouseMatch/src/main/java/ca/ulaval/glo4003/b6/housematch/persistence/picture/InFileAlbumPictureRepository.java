package ca.ulaval.glo4003.b6.housematch.persistence.picture;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Album;
import ca.ulaval.glo4003.b6.housematch.domain.picture.AlbumPictureRepository;

public class InFileAlbumPictureRepository implements AlbumPictureRepository {

   @Override
   public Album getAlbum(String estateAddress) {
      List<String> activePictureNames = new ArrayList<String>();
      return new Album(estateAddress, activePictureNames);
   }

}
