package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.List;

public class Album {

   private List<Picture> pictures;

   public Album(List<Picture> pictures) {
      this.pictures = pictures;
   }

   public void addPicture(Picture file) {
      pictures.add(file);
   }

   public void removePicture(int index) {
      pictures.remove(index);
   }

   public List<Picture> getAllPictures() {
      return pictures;
   }

}