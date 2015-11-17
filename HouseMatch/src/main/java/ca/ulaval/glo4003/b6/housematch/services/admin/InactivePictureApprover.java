package ca.ulaval.glo4003.b6.housematch.services.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePicture;
import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class InactivePictureApprover {

   private InactivePictureRepository inactivePictureRepository;

   private PictureRepository pictureRepository;

   public InactivePictureApprover(InactivePictureRepository inactivePictureRepository,
         PictureRepository pictureRepository) {
      this.inactivePictureRepository = inactivePictureRepository;
      this.pictureRepository = pictureRepository;
   }

   public List<byte[]> getAllInactivePictures() throws CouldNotAccessDataException {
      List<byte[]> pictures = new ArrayList<byte[]>();
      List<InactivePicture> inactivePictures = inactivePictureRepository.getAllInactivePicture();

      for (Iterator<InactivePicture> inactivePictureIterator = inactivePictures.iterator(); inactivePictureIterator
            .hasNext();) {
         InactivePicture inactivePicture = inactivePictureIterator.next();
         pictures.add(pictureRepository.getPicture(inactivePicture.getName(), inactivePicture.getAddress()));
      }
      return pictures;
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
         pictureRepository.deletePicture(inactivePictureIterator.next().getName(),
               inactivePictureIterator.next().getAddress());
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
