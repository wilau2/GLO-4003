package ca.ulaval.glo4003.b6.housematch.services.estate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Album;
import ca.ulaval.glo4003.b6.housematch.domain.picture.AlbumPictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePicture;
import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureSelector;
import ca.ulaval.glo4003.b6.housematch.dto.PictureDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.UUIDAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.PictureAlreadyExistsException;

public class EstatePicturesService {

   private PictureRepository pictureRepository;

   private AlbumPictureRepository albumPictureRepository;

   private InactivePictureRepository inactivePictureRepository;

   @Inject
   public EstatePicturesService(PictureRepository pictureRepository, AlbumPictureRepository albumPictureRepository,
         InactivePictureRepository inactivePictureRepository) {
      this.pictureRepository = pictureRepository;
      this.albumPictureRepository = albumPictureRepository;
      this.inactivePictureRepository = inactivePictureRepository;
   }

   public List<PictureDto> getPicturesOfEstate(String address) {
      Album album = albumPictureRepository.getAlbum(address);

      PictureSelector pictureSelector = album.createCustomPictureSelector(pictureRepository);

      return createPictureModel(pictureSelector.getRelevantPictures());
   }

   public void addPicture(String address, String name, MultipartFile file)
         throws CouldNotAccessDataException, PictureAlreadyExistsException, UUIDAlreadyExistsException {
      Album album = albumPictureRepository.getAlbum(address);

      PictureSelector pictureSelector = album.createCustomPictureSelector(pictureRepository);

      List<String> existingPicturesNames = pictureSelector.getRelevantPictures();
      if (existingPicturesNames.contains(name)) {
         throw new PictureAlreadyExistsException("The picture with name " + name + " already exists");
      }
      pictureSelector.addPicture(name, file);
      inactivePictureRepository.addInactivePicture(new InactivePicture("", address, name));
   }

   public void deletePicture(String address, String name) throws CouldNotAccessDataException {
      Album album = albumPictureRepository.getAlbum(address);

      PictureSelector pictureSelector = album.createCustomPictureSelector(pictureRepository);
      pictureSelector.deletePicture(name);
   }

   public byte[] getPicture(String address, String name) throws CouldNotAccessDataException {
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
