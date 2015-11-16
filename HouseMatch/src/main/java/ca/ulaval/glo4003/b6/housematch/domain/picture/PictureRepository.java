package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface PictureRepository {

   public void deletePicture(String pictureName, String estateAddress);

   public void addPicture(String pictureName, String estateAddress, MultipartFile picture) throws IOException;

   public List<String> getEveryPicturesNames(String estateAddress);

   public byte[] getPicture(String pictureName, String estateAddress) throws IOException;

   public byte[] getEmptyPicture() throws IOException;
}
