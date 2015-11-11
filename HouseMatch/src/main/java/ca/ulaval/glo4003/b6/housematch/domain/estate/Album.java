package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.io.File;
import java.util.List;

public class Album {

   private List<File> pictures;

   public Album(List<File> pictures) {
      this.pictures = pictures;
   }

   public void addPicture(File file) {
      pictures.add(file);
   }

   public void removePicture(int index) {
      pictures.remove(index);
   }

   public List<File> getAllPictures() {
      return pictures;
   }

}
