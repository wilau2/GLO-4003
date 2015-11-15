package ca.ulaval.glo4003.b6.housematch.persistence;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Album;

public class InFileAlbum implements Album {

   @Override
   public void deletePicture(String pictureName, String estateAddress) {
      File fileToDelete = new File(
            "./persistence/uploadedPictures/" + estateAddress + File.separator + pictureName + ".jpg");
      fileToDelete.delete();
   }

   @Override
   public void addPicture(String pictureName, String estateAddress, MultipartFile file) throws IOException {
      if (!file.isEmpty()) {
         byte[] bytes = file.getBytes();
         // Creating the directory to store file
         File dir = new File("./persistence/uploadedPictures/" + estateAddress);
         if (!dir.exists()) {
            dir.mkdirs();
         }
         // Create the file on server
         File serverFile = new File(dir.getAbsolutePath() + File.separator + pictureName + ".jpg");
         BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
         stream.write(bytes);
         stream.close();
      }
   }

   @Override
   public List<String> getEveryPicturesNames(String estateAddress) {
      List<String> nameOfPictures = new ArrayList<String>();
      File dir = new File("./persistence/uploadedPictures/" + estateAddress);
      if (!dir.exists()) {
         dir.mkdirs();
      }
      File[] filesList = dir.listFiles();
      for (File f : filesList) {
         if (f.isFile()) {
            String nameOfPicture = f.getName();
            if (nameOfPicture.indexOf(".") > 0)
               nameOfPicture = nameOfPicture.substring(0, nameOfPicture.lastIndexOf("."));
            nameOfPictures.add(nameOfPicture);
         }
      }
      return nameOfPictures;
   }
}
