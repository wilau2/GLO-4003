package ca.ulaval.glo4003.b6.housematch.services.estate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Album;
import ca.ulaval.glo4003.b6.housematch.domain.picture.AlbumPictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureSelector;
import ca.ulaval.glo4003.b6.housematch.dto.PictureDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class EstatePicturesService {

   private PictureRepository pictureRepository;

   private AlbumPictureRepository albumPictureRepository;

   @Inject
   public EstatePicturesService(PictureRepository pictureRepository, AlbumPictureRepository albumPictureRepository) {
      this.pictureRepository = pictureRepository;
      this.albumPictureRepository = albumPictureRepository;
   }

   public List<PictureDto> getPicturesOfEstate(String address)
         throws EstateNotFoundException, CouldNotAccessDataException {
      Album album = albumPictureRepository.getAlbum(address);

      PictureSelector pictureSelector = album.createCustomPictureSelector(pictureRepository);

      return createPictureModel(pictureSelector.getRelevantPictures());
   }

   public void addPicture(String address, String name, MultipartFile file)
         throws EstateNotFoundException, CouldNotAccessDataException, IOException {
      Album album = albumPictureRepository.getAlbum(address);

      PictureSelector pictureSelector = album.createCustomPictureSelector(pictureRepository);

      pictureSelector.addPicture(name, file);
   }

   public void deletePicture(String address, String name)
         throws EstateNotFoundException, CouldNotAccessDataException, IOException {
      Album album = albumPictureRepository.getAlbum(address);

      PictureSelector pictureSelector = album.createCustomPictureSelector(pictureRepository);
      pictureSelector.deletePicture(name);
   }

   public byte[] getPicture(String address, String name)
         throws IOException, EstateNotFoundException, CouldNotAccessDataException {
      Album album = albumPictureRepository.getAlbum(address);

      PictureSelector pictureSelector = album.createCustomPictureSelector(pictureRepository);
      return pictureSelector.getPicture(name);
   }

   public List<PictureDto> createPictureModel(List<String> picturesNames) {
      List<PictureDto> pictures = new ArrayList<PictureDto>();
      for (String pictureName : picturesNames)
         pictures.add(new PictureDto(pictureName));
      return pictures;
   }

}
