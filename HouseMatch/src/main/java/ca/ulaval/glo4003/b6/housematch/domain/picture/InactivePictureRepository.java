package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.List;

public interface InactivePictureRepository {

   void addInactivePicture(InactivePicture inactivePicture);

   void deleteInactivePicture(InactivePicture inactivePicture);

   List<InactivePicture> getAllInactivePicture();

}
