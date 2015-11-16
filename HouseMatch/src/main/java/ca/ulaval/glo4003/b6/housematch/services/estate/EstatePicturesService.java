package ca.ulaval.glo4003.b6.housematch.services.estate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.PhotoAlbum;
import ca.ulaval.glo4003.b6.housematch.domain.estate.PicturesSelector;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.PictureDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class EstatePicturesService {

   private EstateRepositoryFactory estateRepositoryFactory;

   private EstateAssemblerFactory estateAssemblerFactory;

   private PhotoAlbum photoAlbum;

   @Inject
   public EstatePicturesService(EstateAssemblerFactory estateAssemblerFactory,
         EstateRepositoryFactory estateRepositoryFactory, PhotoAlbum photoAlbum) {
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepositoryFactory = estateRepositoryFactory;
      this.photoAlbum = photoAlbum;
   }

   public List<PictureDto> getPicturesOfEstate(String address)
         throws EstateNotFoundException, CouldNotAccessDataException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      Estate estate = estateRepository.getEstateByAddress(address);

      PicturesSelector picturesSelector = estate.makeCustomPictureSelector();

      return createPictureDtos(picturesSelector.getRelevantPicturesUrl(photoAlbum));
   }

   public void addPicture(String address, String name, MultipartFile file)
         throws EstateNotFoundException, CouldNotAccessDataException, IOException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      Estate estate = estateRepository.getEstateByAddress(address);

      PicturesSelector picturesSelector = estate.makeCustomPictureSelector();

      picturesSelector.addPicture(name, file, photoAlbum);
   }

   public void deletePicture(String address, String name)
         throws EstateNotFoundException, CouldNotAccessDataException, IOException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      Estate estate = estateRepository.getEstateByAddress(address);

      PicturesSelector picturesSelector = estate.makeCustomPictureSelector();

      picturesSelector.deletePicture(name, photoAlbum);
   }

   public byte[] getPicture(String address, String name)
         throws IOException, EstateNotFoundException, CouldNotAccessDataException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      Estate estate = estateRepository.getEstateByAddress(address);

      PicturesSelector picturesSelector = estate.makeCustomPictureSelector();

      return picturesSelector.getPicture(name, photoAlbum);
   }

   public List<PictureDto> createPictureDtos(List<String> picturesNames) {
      List<PictureDto> pictures = new ArrayList<PictureDto>();
      for (String pictureName : picturesNames)
         pictures.add(new PictureDto(pictureName));
      return pictures;
   }

}
