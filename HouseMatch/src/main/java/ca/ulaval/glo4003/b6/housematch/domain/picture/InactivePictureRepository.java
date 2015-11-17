package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.UUIDAlreadyExistsException;

public interface InactivePictureRepository {

   void addInactivePicture(InactivePicture inactivePicture)
         throws UUIDAlreadyExistsException, CouldNotAccessDataException;

   void deleteInactivePicture(InactivePicture inactivePicture) throws CouldNotAccessDataException;

   List<InactivePicture> getAllInactivePicture() throws CouldNotAccessDataException;

}
