package ca.ulaval.glo4003.b6.housematch.services.admin;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.domain.picture.ApprovalPictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Pictures;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.UUIDAlreadyExistsException;

public class PictureApprobationService {

   private ApprovalPictureRepository approvalPictureRepository;

   private PictureRepository pictureRepository;

   @Inject
   public PictureApprobationService(ApprovalPictureRepository inactivePictureRepository,
         PictureRepository pictureRepository) {
      this.approvalPictureRepository = inactivePictureRepository;
      this.pictureRepository = pictureRepository;
   }

   public List<Picture> getAllInactivePictures() throws CouldNotAccessDataException {
      Pictures pictures = approvalPictureRepository.getAllPictures();
      return pictures.getInactivePictures();

   }

   public byte[] getInactivePictureContent(String uid) throws CouldNotAccessDataException {

      Picture inactivePicture = approvalPictureRepository.getPictureByUid(uid);
      return pictureRepository.getPicture(inactivePicture.getName(), inactivePicture.getAddress());

   }

   public void approuvePictures(List<String> approvalPictureUids)
         throws CouldNotAccessDataException, UUIDAlreadyExistsException {
      Pictures pictures = approvalPictureRepository.getAllPictures();
      List<Picture> activatedPictures = pictures.activatePicturesFromUids(approvalPictureUids);
      approvalPictureRepository.updatePictures(activatedPictures);
   }

   public void unapprouvePictures(List<String> approvalPictureUids) throws CouldNotAccessDataException {
      deletePicturesFromPictureRepository(approvalPictureUids);
      deletePicturesFromApprovalRepository(approvalPictureUids);
   }

   private void deletePicturesFromPictureRepository(List<String> inactivePictureUids)
         throws CouldNotAccessDataException {
      List<Picture> inactivePictures = approvalPictureRepository.getPicturesByUids(inactivePictureUids);
      for (Iterator<Picture> inactivePictureIterator = inactivePictures.iterator(); inactivePictureIterator
            .hasNext();) {
         Picture inactivePicture = inactivePictureIterator.next();
         pictureRepository.deletePicture(inactivePicture.getName(), inactivePicture.getAddress());
      }
   }

   private void deletePicturesFromApprovalRepository(List<String> inactivePictureUids)
         throws CouldNotAccessDataException {
      for (Iterator<String> inactivePictureUidsIterator = inactivePictureUids.iterator(); inactivePictureUidsIterator
            .hasNext();) {
         approvalPictureRepository.deletePicture(inactivePictureUidsIterator.next());
      }
   }

}
