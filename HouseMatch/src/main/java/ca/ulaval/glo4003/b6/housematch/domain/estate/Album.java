package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface Album {

   public void deletePicture(String pictureName, String estateAddress);

   public void addPicture(String pictureName, String estateAddress, MultipartFile file) throws IOException;

   public List<String> getEveryPicturesNames(String estateAddress);
}
