package ca.ulaval.glo4003.b6.housematch.domain.picture;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public interface PictureRepository {

   public void deletePicture(String pictureName, String estateAddress);

   public void addPicture(String pictureName, String estateAddress, MultipartFile picture)
         throws CouldNotAccessDataException;

   public List<String> getEveryPicturesNames(String estateAddress);

   public byte[] getPicture(String pictureName, String estateAddress) throws CouldNotAccessDataException;

   public byte[] getEmptyPicture() throws CouldNotAccessDataException;
}
