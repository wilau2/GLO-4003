package ca.ulaval.glo4003.b6.housematch.services.picture;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Album;
import ca.ulaval.glo4003.b6.housematch.domain.picture.AlbumFactory;
import ca.ulaval.glo4003.b6.housematch.domain.picture.ApprovalPictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.domain.picture.PictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Pictures;
import ca.ulaval.glo4003.b6.housematch.dto.PictureDto;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.UUIDAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.PictureAlreadyExistsException;

public class EstatePicturesService {

   private PictureRepository pictureRepository;

   private AlbumFactory albumPictureFactory;

   private ApprovalPictureRepository approvalPictureRepository;

   @Inject
   public EstatePicturesService(PictureRepository pictureRepository, AlbumFactory albumPictureFactory,
         ApprovalPictureRepository approvalPictureRepository) {

      this.pictureRepository = pictureRepository;
      this.albumPictureFactory = albumPictureFactory;
      this.approvalPictureRepository = approvalPictureRepository;

   }

   public List<PictureDto> getPublicPicturesOfEstate(String address) throws CouldNotAccessDataException {

      Pictures pictures = approvalPictureRepository.getAllPictures();
      List<String> estatePicturesNames = pictures.getActiveEstatePicturesNames(address);
      Album album = albumPictureFactory.createAlbum(estatePicturesNames, address);

      return createPictureModel(album.getRelevantPictures());
   }

   public List<PictureDto> getPrivatePicturesOfEstate(String address) throws CouldNotAccessDataException {

      Pictures pictures = approvalPictureRepository.getAllPictures();
      List<String> estatePicturesNames = pictures.getInactiveEstatePicturesNames(address);

      return createInactivePictureModel(estatePicturesNames);
   }

   public void addPicture(String address, String name, MultipartFile file)
         throws CouldNotAccessDataException, PictureAlreadyExistsException, UUIDAlreadyExistsException {
      Album album = albumPictureFactory.createAlbum(address);

      List<String> existingPicturesNames = album.getRelevantPictures();
      if (existingPicturesNames.contains(name)) {
         throw new PictureAlreadyExistsException("The picture with name " + name + " already exists");
      }
      pictureRepository.addPicture(name, address, file);
      approvalPictureRepository.addPicture(new Picture("", address, name, ""));
   }

   public void deletePicture(String address, String name) throws CouldNotAccessDataException {
      pictureRepository.deletePicture(name, address);

      Pictures pictures = approvalPictureRepository.getAllPictures();
      String uid = pictures.getEstatePictureUid(address, name);
      approvalPictureRepository.deletePicture(uid);
   }

   public byte[] getPicture(String address, String name) throws CouldNotAccessDataException {
      Album album = albumPictureFactory.createAlbum(address);

      return album.getSpecificPicture(name, pictureRepository);
   }

   public List<PictureDto> createPictureModel(List<String> picturesNames) {
      List<PictureDto> pictures = new ArrayList<PictureDto>();
      for (String pictureName : picturesNames)
         pictures.add(new PictureDto(pictureName));
      return pictures;
   }

   public List<PictureDto> createInactivePictureModel(List<String> picturesNames) {
      List<PictureDto> pictures = createPictureModel(picturesNames);
      for (PictureDto pictureDto : pictures)
         pictureDto.setActive("false");
      return pictures;
   }
}
