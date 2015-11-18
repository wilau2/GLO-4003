package ca.ulaval.glo4003.b6.housematch.services.estate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Album;
import ca.ulaval.glo4003.b6.housematch.domain.picture.AlbumPictureFactory;
import ca.ulaval.glo4003.b6.housematch.domain.picture.ApprovalPictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureSelector;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Pictures;
import ca.ulaval.glo4003.b6.housematch.dto.PictureDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.UUIDAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.PictureAlreadyExistsException;

public class EstatePicturesService {

   private PictureRepository pictureRepository;

   private AlbumPictureFactory albumPictureFactory;

   private ApprovalPictureRepository approvalPictureRepository;

   @Inject
   public EstatePicturesService(PictureRepository pictureRepository, AlbumPictureFactory albumPictureFactory,
         ApprovalPictureRepository approvalPictureRepository) {
      this.pictureRepository = pictureRepository;
      this.albumPictureFactory = albumPictureFactory;
      this.approvalPictureRepository = approvalPictureRepository;
   }

   public List<PictureDto> getPicturesOfEstate(String address) throws CouldNotAccessDataException {

      Pictures pictures = approvalPictureRepository.getAllPictures();
      List<String> estatePicturesNames = pictures.getActiveEstatePicturesNames(address);
      Album album = albumPictureFactory.createAlbum(estatePicturesNames, address);

      PictureSelector pictureSelector = album.createCustomPictureSelector(pictureRepository);

      return createPictureModel(pictureSelector.getRelevantPictures());
   }

   public void addPicture(String address, String name, MultipartFile file)
         throws CouldNotAccessDataException, PictureAlreadyExistsException, UUIDAlreadyExistsException {
      Album album = albumPictureFactory.createAlbum(address);

      PictureSelector pictureSelector = album.createCustomPictureSelector(pictureRepository);

      List<String> existingPicturesNames = pictureSelector.getRelevantPictures();
      if (existingPicturesNames.contains(name)) {
         throw new PictureAlreadyExistsException("The picture with name " + name + " already exists");
      }
      pictureSelector.addPicture(name, file);
      approvalPictureRepository.addPicture(new Picture("", address, name, ""));
   }

   public void deletePicture(String address, String name) throws CouldNotAccessDataException {
      Album album = albumPictureFactory.createAlbum(address);

      PictureSelector pictureSelector = album.createCustomPictureSelector(pictureRepository);
      pictureSelector.deletePicture(name);
   }

   public byte[] getPicture(String address, String name) throws CouldNotAccessDataException {
      Album album = albumPictureFactory.createAlbum(address);

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
