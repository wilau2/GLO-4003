package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.ArrayList;
import java.util.List;

public class Pictures {

   private List<Picture> pictures;

   public Pictures(List<Picture> pictures) {
      this.pictures = pictures;
   }

   public List<Picture> getActivePictures() {
      List<Picture> activePictures = new ArrayList<Picture>();
      for (Picture picture : pictures) {
         if (picture.isActive()) {
            activePictures.add(picture);
         }
      }
      return activePictures;
   }

   public List<Picture> getInactivePictures() {
      List<Picture> inactivePictures = new ArrayList<Picture>();

      for (Picture picture : pictures) {
         if (!picture.isActive()) {
            inactivePictures.add(picture);
         }
      }
      return inactivePictures;
   }

   public String getEstatePictureUid(String address, String name) {
      String uid = "";
      for (Picture picture : pictures) {
         if (picture.isFromEstate(address) && picture.getName().equals(name)) {
            uid = picture.getUid();
         }
      }
      return uid;
   }

   public List<String> getActiveEstatePicturesNames(String address) {
      pictures = getActivePictures();
      List<String> activeEstatePicturesNames = new ArrayList<String>();

      for (Picture picture : pictures) {
         if (picture.isFromEstate(address)) {
            activeEstatePicturesNames.add(picture.getName());
         }
      }
      return activeEstatePicturesNames;
   }

   public List<String> getInactiveEstatePicturesNames(String address) {
      pictures = getInactivePictures();
      List<String> activeEstatePicturesNames = new ArrayList<String>();

      for (Picture picture : pictures) {
         if (picture.isFromEstate(address)) {
            activeEstatePicturesNames.add(picture.getName());
         }
      }
      return activeEstatePicturesNames;
   }

   List<Picture> getPicturesFromUids(List<String> uids) {
      List<Picture> picturesFromUids = new ArrayList<Picture>();

      for (Picture picture : pictures) {
         if (uids.contains(picture.getUid())) {
            picturesFromUids.add(picture);
         }
      }
      return picturesFromUids;
   }

   public List<Picture> activatePicturesFromUids(List<String> uids) {
      List<Picture> pictureList = new ArrayList<Picture>();

      for (Picture picture : pictures) {
         if (uids.contains(picture.getUid())) {
            picture.activate();
            pictureList.add(picture);
         }
      }
      return pictureList;
   }
}
