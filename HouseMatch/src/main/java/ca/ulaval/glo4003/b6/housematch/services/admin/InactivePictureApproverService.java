package ca.ulaval.glo4003.b6.housematch.services.admin;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePicture;
import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class InactivePictureApproverService {

   private InactivePictureRepository inactivePictureRepository;

   private PictureRepository pictureRepository;

   @Inject
   public InactivePictureApproverService(InactivePictureRepository inactivePictureRepository,
         PictureRepository pictureRepository) {
      this.inactivePictureRepository = inactivePictureRepository;
      this.pictureRepository = pictureRepository;
   }

   public List<InactivePicture> getAllInactivePictures() throws CouldNotAccessDataException {

      return inactivePictureRepository.getAllInactivePicture();

   }

   public byte[] getInactivePictureByte(String uid) throws CouldNotAccessDataException {

      InactivePicture inactivePicture = inactivePictureRepository.getInactivePictureByUid(uid);
      return pictureRepository.getPicture(inactivePicture.getName(), inactivePicture.getAddress());

   }

   public void approuvePictures(List<String> inactivePictureUids) throws CouldNotAccessDataException {
      // TODO DO SOMETHING TO APPROUVE
      deletePicturesFromInactiveRepository(inactivePictureUids);
   }

   public void unapprouvePictures(List<String> inactivePictureUids) throws CouldNotAccessDataException {

      deletePicturesFromPictureRepository(inactivePictureUids);
      deletePicturesFromInactiveRepository(inactivePictureUids);
   }

   private void deletePicturesFromPictureRepository(List<String> inactivePictureUids)
         throws CouldNotAccessDataException {
      List<InactivePicture> inactivePictures = inactivePictureRepository.getInactivePicturesByUids(inactivePictureUids);
      for (Iterator<InactivePicture> inactivePictureIterator = inactivePictures.iterator(); inactivePictureIterator
            .hasNext();) {
         InactivePicture inactivePicture = inactivePictureIterator.next();
         pictureRepository.deletePicture(inactivePicture.getName(), inactivePicture.getAddress());
      }
   }

   private void deletePicturesFromInactiveRepository(List<String> inactivePictureUids)
         throws CouldNotAccessDataException {
      for (Iterator<String> inactivePictureUidsIterator = inactivePictureUids.iterator(); inactivePictureUidsIterator
            .hasNext();) {
         inactivePictureRepository.deleteInactivePicture(inactivePictureUidsIterator.next());
      }
   }

}
