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
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class InFilePictureRepository implements PictureRepository {

   @Override
   public void deletePicture(String pictureName, String estateAddress) {
      File fileToDelete = new File(
            "./persistence/uploadedPictures/" + estateAddress + File.separator + pictureName + ".jpg");
      fileToDelete.delete();
   }

   @Override
   public void addPicture(String pictureName, String estateAddress, MultipartFile picture)
         throws CouldNotAccessDataException {
      try {
         if (!picture.isEmpty()) {

            File directory = makeSpecificEstateDirectory(estateAddress);
            File serverFile = new File(directory.getAbsolutePath() + File.separator + pictureName + ".jpg");

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

            byte[] bytes = picture.getBytes();
            stream.write(bytes);
            stream.close();
         }
      } catch (IOException ioException) {
         throw new CouldNotAccessDataException("Could not access the stored pictures data", ioException);
      }
   }

   @Override
   public List<String> getEveryPicturesNames(String estateAddress) {

      File directory = makeSpecificEstateDirectory(estateAddress);

      List<String> nameOfPictures = new ArrayList<String>();
      File[] filesList = directory.listFiles();
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
   public byte[] getPicture(String pictureName, String estateAddress) throws CouldNotAccessDataException {
      try {

         File directory = makeSpecificEstateDirectory(estateAddress);

         File serverFile = new File(directory.getAbsolutePath() + File.separator + pictureName + ".jpg");

         ByteArrayOutputStream outputStream = makeOutputStream(serverFile);
         return outputStream.toByteArray();

      } catch (IOException ioException) {
         throw new CouldNotAccessDataException("Could not access the stored pictures data", ioException);
      }

   }

   @Override
   public byte[] getEmptyPicture() throws CouldNotAccessDataException {

      try {
         File directory = makeSpecificEstateDirectory("DefaultPicture");

         File serverFile = new File(directory.getAbsolutePath() + File.separator + "NoPhoto.jpg");

         ByteArrayOutputStream outputStream = makeOutputStream(serverFile);
         return outputStream.toByteArray();

      } catch (IOException ioException) {
         throw new CouldNotAccessDataException("Could not access the stored pictures data", ioException);
      }
   }

   private File makeSpecificEstateDirectory(String estateAddress) {
      File directory = new File("./persistence/uploadedPictures/" + estateAddress);
      if (!directory.exists()) {
         directory.mkdirs();
      }
      return directory;
   }

   private ByteArrayOutputStream makeOutputStream(File serverFile) throws IOException {
      InputStream inputStream = new FileInputStream(serverFile);
      BufferedImage picture = ImageIO.read(inputStream);

      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      ImageIO.write(picture, "jpg", outputStream);
      return outputStream;
   }
}
