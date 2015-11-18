package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pictures {

   List<Picture> pictures;

   public Pictures(List<Picture> pictures) {
      this.pictures = pictures;
   }

   public List<Picture> getPictures() {
      return this.pictures;
   }

   public List<Picture> getActivePictures() {
      List<Picture> activePictures = new ArrayList<Picture>();
      for (Iterator<Picture> picturesIterator = pictures.iterator(); picturesIterator.hasNext();) {
         Picture picture = picturesIterator.next();
         if (picture.isActive()) {
            activePictures.add(picture);
         }
      }
      return activePictures;
   }

   public List<Picture> getInactivePictures() {
      List<Picture> inactivePictures = new ArrayList<Picture>();
      for (Iterator<Picture> picturesIterator = pictures.iterator(); picturesIterator.hasNext();) {
         Picture picture = picturesIterator.next();
         if (!picture.isActive()) {
            inactivePictures.add(picture);
         }
      }
      return inactivePictures;
   }

   public List<String> getActiveEstatePicturesNames(String address) {
      this.pictures = getActivePictures();
      List<String> activeEstatePicturesNames = new ArrayList<String>();

      for (Iterator<Picture> picturesIterator = this.pictures.iterator(); picturesIterator.hasNext();) {
         Picture picture = picturesIterator.next();
         if (picture.isFromEstate(address)) {
            activeEstatePicturesNames.add(picture.getName());
         }
      }
      return activeEstatePicturesNames;
   }

   public List<Picture> getPicturesFromUids(List<String> uids) {
      List<Picture> pictures = new ArrayList<Picture>();
      for (Iterator<Picture> picturesIterator = pictures.iterator(); picturesIterator.hasNext();) {
         Picture picture = picturesIterator.next();
         if (uids.contains(picture.getUid())) {
            pictures.add(picture);
         }
      }
      return pictures;
   }

   public List<Picture> activatePicturesFromUids(List<String> uids) {
      List<Picture> pictureList = new ArrayList<Picture>();
      for (Iterator<Picture> picturesIterator = this.pictures.iterator(); picturesIterator.hasNext();) {
         Picture picture = picturesIterator.next();
         if (uids.contains(picture.getUid())) {
            picture.activate();
            pictureList.add(picture);
         }
      }
      return pictureList;

   }
}
