package ca.ulaval.glo4003.b6.housematch.persistence.picture;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

   private static final String PATH_TO_PICTURES = "./persistence/uploadedPictures/";

   @Override
   public void deletePicture(String pictureName, String estateAddress) {
      File fileToDelete = new File(PATH_TO_PICTURES + estateAddress + File.separator + pictureName + ".jpg");
      fileToDelete.delete();
   }

   @Override
   public void addPicture(String pictureName, String estateAddress, MultipartFile picture)
         throws CouldNotAccessDataException {
      try {
         if (!picture.isEmpty()) {
            byte[] bytes = picture.getBytes();
            // Creating the directory to store file
            File directory = new File(PATH_TO_PICTURES + estateAddress);
            validateDirectoryExists(directory);
            // Create the file on server
            createFileOnDisk(pictureName, bytes, directory);
         }
      } catch (IOException ioException) {
         throw new CouldNotAccessDataException("Could not access the stored pictures data", ioException);
      }
   }

   private void createFileOnDisk(String pictureName, byte[] bytes, File directory)
         throws FileNotFoundException, IOException {
      File serverFile = new File(directory.getAbsolutePath() + File.separator + pictureName + ".jpg");
      BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
      stream.write(bytes);
      stream.close();
   }

   protected void validateDirectoryExists(File dir) {
      if (!dir.exists()) {
         dir.mkdirs();
      }
   }

   @Override
   public List<String> getEveryPicturesNames(String estateAddress) {
      List<String> nameOfPictures = new ArrayList<String>();
      File dir = new File(PATH_TO_PICTURES + estateAddress);
      validateDirectoryExists(dir);
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
   public byte[] getPicture(String pictureName, String estateAddress) throws CouldNotAccessDataException {
      try {
         File dir = new File(PATH_TO_PICTURES + estateAddress);
         validateDirectoryExists(dir);

         // Create the file on server
         File serverFile = new File(dir.getAbsolutePath() + File.separator + pictureName + ".jpg");
         InputStream inputStream = new FileInputStream(serverFile);
         BufferedImage picture = ImageIO.read(inputStream);

         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

         ImageIO.write(picture, "jpg", outputStream);

         return outputStream.toByteArray();
      } catch (IOException ioException) {
         throw new CouldNotAccessDataException("Could not access the stored pictures data", ioException);
      }
   }

   @Override
   public byte[] getEmptyPicture() throws CouldNotAccessDataException {

      try {
         File dir = new File("./persistence/uploadedPictures/DefaultPicture");
         validateDirectoryExists(dir);

         // Create the file on server
         File serverFile = new File(dir.getAbsolutePath() + File.separator + "NoPhoto.jpg");
         InputStream inputStream = new FileInputStream(serverFile);
         BufferedImage picture = ImageIO.read(inputStream);

         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

         ImageIO.write(picture, "jpg", outputStream);

         return outputStream.toByteArray();

      } catch (IOException ioException) {
         throw new CouldNotAccessDataException("Could not access the stored pictures data", ioException);
      }
   }
}
