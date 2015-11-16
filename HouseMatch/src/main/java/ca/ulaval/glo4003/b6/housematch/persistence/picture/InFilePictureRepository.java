package ca.ulaval.glo4003.b6.housematch.persistence.picture;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;

public class InFilePictureRepository implements PictureRepository {

   @Override
   public void deletePicture(String pictureName, String estateAddress) {
      File fileToDelete = new File(
            "./persistence/uploadedPictures/" + estateAddress + File.separator + pictureName + ".jpg");
      fileToDelete.delete();
   }

   @Override
   public void addPicture(String pictureName, String estateAddress, MultipartFile picture) throws IOException {
      if (!picture.isEmpty()) {
         byte[] bytes = picture.getBytes();
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

   @Override
   public byte[] getPicture(String pictureName, String estateAddress) throws IOException {

      File dir = new File("./persistence/uploadedPictures/" + estateAddress);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      // Create the file on server
      File serverFile = new File(dir.getAbsolutePath() + File.separator + pictureName + ".jpg");
      InputStream inputStream = new FileInputStream(serverFile);
      BufferedImage picture = ImageIO.read(inputStream);

      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

      ImageIO.write(picture, "jpg", outputStream);

      return outputStream.toByteArray();
   }

   @Override
   public byte[] getDefaultPicture() throws IOException {
      File dir = new File("./persistence/uploadedPictures/DefaultPicture");
      if (!dir.exists()) {
         dir.mkdirs();
      }

      // Create the file on server
      File serverFile = new File(dir.getAbsolutePath() + File.separator + "NoPhoto.jpg");
      InputStream inputStream = new FileInputStream(serverFile);
      BufferedImage picture = ImageIO.read(inputStream);

      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

      ImageIO.write(picture, "jpg", outputStream);

      return outputStream.toByteArray();
   }
}
