package ca.ulaval.glo4003.b6.housematch.services.estate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Album;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureSelector;
import ca.ulaval.glo4003.b6.housematch.dto.PictureDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class EstatePicturesService {

   private PictureRepository pictureRepository;

   @Inject
   public EstatePicturesService(PictureRepository pictureRepository) {
      this.pictureRepository = pictureRepository;
   }

   public List<PictureDto> getPicturesOfEstate(String address)
         throws EstateNotFoundException, CouldNotAccessDataException {
      List<String> activePictureNames = new ArrayList<String>();
      Album album = new Album(address, activePictureNames);

      PictureSelector picturesSelector = new PictureSelector(album, pictureRepository);

      return createPictureDtos(picturesSelector.getRelevantPictures());
   }

   public void addPicture(String address, String name, MultipartFile file)
         throws EstateNotFoundException, CouldNotAccessDataException, IOException {
      List<String> activePictureNames = new ArrayList<String>();
      Album album = new Album(address, activePictureNames);

      PictureSelector picturesSelector = new PictureSelector(album, pictureRepository);
      picturesSelector.addPicture(name, file);

   }

   public void deletePicture(String address, String name)
         throws EstateNotFoundException, CouldNotAccessDataException, IOException {
      List<String> activePictureNames = new ArrayList<String>();
      Album album = new Album(address, activePictureNames);

      PictureSelector picturesSelector = new PictureSelector(album, pictureRepository);
      picturesSelector.deletePicture(name);
   }

   public byte[] getPicture(String address, String name)
         throws IOException, EstateNotFoundException, CouldNotAccessDataException {
      List<String> activePictureNames = new ArrayList<String>();
      Album album = new Album(address, activePictureNames);

      PictureSelector picturesSelector = new PictureSelector(album, pictureRepository);
      return picturesSelector.getPicture(name);

   }

   public List<PictureDto> createPictureDtos(List<String> picturesNames) {
      List<PictureDto> pictures = new ArrayList<PictureDto>();
      for (String pictureName : picturesNames)
         pictures.add(new PictureDto(pictureName));
      return pictures;
   }

}
