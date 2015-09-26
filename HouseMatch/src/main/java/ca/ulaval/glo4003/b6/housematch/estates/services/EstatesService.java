package ca.ulaval.glo4003.b6.housematch.estates.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.AddressAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.AddressAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;

public class EstatesService {

   private EstateValidatorFactory estateValidatorFactory;

   private EstateRepository estateRepository;

   private EstateAssemblerFactory estateAssemblerFactory;
   
   private AddressAssemblerFactory addressAssemblerFactory;

   @Autowired
   public EstatesService(EstateValidatorFactory estateValidatorFactory, EstateAssemblerFactory estateAssemblerFactory,
         AddressAssemblerFactory addressAssemblerFactory,EstateRepositoryFactory estateRepositoryFactory, 
         EstatePersistenceDtoFactory estatePersistenceDtoFactory) {

      this.estateValidatorFactory = estateValidatorFactory;
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.addressAssemblerFactory = addressAssemblerFactory;
      this.estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory, estatePersistenceDtoFactory);

   }

   public void addEstate(EstateDto estateDto) throws InvalidEstateException {
      EstateValidator estateValidator = estateValidatorFactory.getValidator();
      estateValidator.validate(estateDto);

      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();
      AddressAssembler addressAssembler = addressAssemblerFactory.createAddressAssembler();
      Estate estate = estateAssembler.assembleEstate(estateDto, addressAssembler);

      estateRepository.addEstate(estate);
   }

   public List<EstateDto> getAllEstates() {
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();
      AddressAssembler addressAssembler = addressAssemblerFactory.createAddressAssembler();
      List<Estate> estateList = estateRepository.getAllEstates();

      List<EstateDto> estatesDto = new ArrayList<EstateDto>();
      for (Estate estate : estateList) {
         estatesDto.add(estateAssembler.assembleEstateDto(estate, addressAssembler));
      }

      return estatesDto;

   }

   public void editEstate(EstateDto estateDto) throws InvalidEstateException {
      EstateValidator estateValidator = estateValidatorFactory.getValidator();
      estateValidator.validate(estateDto);

      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();
      AddressAssembler addressAssembler = addressAssemblerFactory.createAddressAssembler();
      Estate estate = estateAssembler.assembleEstate(estateDto, addressAssembler);

      estateRepository.editEstate(estate);
   }

}
