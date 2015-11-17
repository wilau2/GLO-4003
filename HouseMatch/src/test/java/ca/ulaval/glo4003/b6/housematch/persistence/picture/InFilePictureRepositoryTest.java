package ca.ulaval.glo4003.b6.housematch.persistence.picture;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class InFilePictureRepositoryTest {

   private InFilePictureRepository inFilePictureRepository;

   private final String EXISTINGPICTURE = "firstPicture";

   private final String NEWPICTURE = "NewPicture";

   private final String TESTADDRESS = "testData";

   @Before
   public void setup() {
      inFilePictureRepository = new InFilePictureRepository();
   }

   @Test
   public void getEveryPicturesNamesShouldReturnTheNamesOfTheFiles() {
      // When
      List<String> returnedNames = inFilePictureRepository.getEveryPicturesNames(TESTADDRESS);
      // Then
      assertTrue(returnedNames.contains(EXISTINGPICTURE));
   }

   @Test
   public void shouldBeAbleToAddNewPicture() throws IOException {
      // Given
      File file = new File("./persistence/uploadedPictures/testData/firstPicture.jpg");
      FileInputStream input = new FileInputStream(file);
      MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
            IOUtils.toByteArray(input));
      // When
      inFilePictureRepository.addPicture(NEWPICTURE, TESTADDRESS, multipartFile);
      // Then
      List<String> returnedNames = inFilePictureRepository.getEveryPicturesNames(TESTADDRESS);
      assertTrue(returnedNames.contains(NEWPICTURE));
   }

   @Test
   public void shouldBeAbleToDeleteAPicture() throws IOException {
      // When
      inFilePictureRepository.deletePicture(NEWPICTURE, TESTADDRESS);
      // Then
      List<String> returnedNames = inFilePictureRepository.getEveryPicturesNames(TESTADDRESS);
      assertFalse(returnedNames.contains(NEWPICTURE));
   }

   @Test
   public void shouldBeAbleToGetTheDefaultPicture() throws IOException {
      // When
      byte[] array1 = inFilePictureRepository.getEmptyPicture();
      byte[] array2 = inFilePictureRepository.getPicture("NoPhoto", "DefaultPicture");
      // Then verify that there is actual content in the file and that the twos
      // arrays are the same
      assertTrue(array1.length > 1);
      assertTrue(Arrays.equals(array1, array2));
   }

}
