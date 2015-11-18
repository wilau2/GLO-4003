package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.UUIDAlreadyExistsException;

public interface ApprovalPictureRepository {

   void addPicture(Picture inactivePicture) throws UUIDAlreadyExistsException, CouldNotAccessDataException;

   Pictures getAllPictures() throws CouldNotAccessDataException;

   void deletePicture(String uid) throws CouldNotAccessDataException;

   Picture getPictureByUid(String uid) throws CouldNotAccessDataException;

   List<Picture> getPicturesByUids(List<String> uids) throws CouldNotAccessDataException;

   void updatePictures(List<Picture> pictures) throws CouldNotAccessDataException, UUIDAlreadyExistsException;

}
