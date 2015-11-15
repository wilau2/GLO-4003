package ca.ulaval.glo4003.b6.housematch.services.estate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Picture;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.SellerNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;

public class EstatesFetcher {

   private EstateRepositoryFactory estateRepositoryFactory;

   private EstateAssemblerFactory estateAssemblerFactory;

   public EstatesFetcher(EstateAssemblerFactory estateAssemblerFactory,
         EstateRepositoryFactory estateRepositoryFactory) {
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepositoryFactory = estateRepositoryFactory;
   }

   public List<EstateDto> getEstatesBySeller(String sellerName)
         throws SellerNotFoundException, CouldNotAccessDataException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);

      List<Estate> sellerEstates = estateRepository.getEstateFromSeller(sellerName);

      List<EstateDto> sellerEstatesDto = assembleEstatesDto(sellerEstates);

      return sellerEstatesDto;
   }

   public EstateDto getEstateByAddress(String address) throws EstateNotFoundException, CouldNotAccessDataException {

      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      Estate estate = estateRepository.getEstateByAddress(address);

      EstateAssembler createEstateAssembler = estateAssemblerFactory.createEstateAssembler();
      EstateDto estateDto = createEstateAssembler.assembleEstateDto(estate);

      return estateDto;
   }

   public List<EstateDto> getAllEstates() throws CouldNotAccessDataException {

      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      List<Estate> estates = estateRepository.getAllEstates();

      List<EstateDto> estatesDto = assembleEstatesDto(estates);

      return estatesDto;

   }

   public List<Picture> getPicturesOfEstate(String address)
         throws EstateNotFoundException, CouldNotAccessDataException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      Estate estate = estateRepository.getEstateByAddress(address);

      return estate.getEveryPictures();
   }

   public void addPicture(String address, String name, MultipartFile file)
         throws EstateNotFoundException, CouldNotAccessDataException, IOException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);

      Estate estate = estateRepository.getEstateByAddress(address);

      estate.addPicture(name, file);
   }

   public void deletePicture(String address, String name)
         throws EstateNotFoundException, CouldNotAccessDataException, IOException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);

      Estate estate = estateRepository.getEstateByAddress(address);

      estate.deletePicture(name);
   }

   private List<EstateDto> assembleEstatesDto(List<Estate> estates) {
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      List<EstateDto> estatesDto = new ArrayList<EstateDto>();
      for (Estate estate : estates) {
         EstateDto assembledEstateDto = estateAssembler.assembleEstateDto(estate);
         estatesDto.add(assembledEstateDto);
      }

      return estatesDto;
   }

}
