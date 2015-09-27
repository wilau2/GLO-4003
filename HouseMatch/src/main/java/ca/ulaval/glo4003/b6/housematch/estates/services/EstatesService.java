package ca.ulaval.glo4003.b6.housematch.estates.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.AddressValidator;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.factories.AddressValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.factories.EstateValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;

public class EstatesService {

   private EstateValidatorFactory estateValidatorFactory;

   private EstateRepositoryFactory estateRepositoryFactory;

   private EstateAssemblerFactory estateAssemblerFactory;

   private AddressValidatorFactory addressValidatorFactory;

   @Autowired
   public EstatesService(EstateValidatorFactory estateValidatorFactory, EstateAssemblerFactory estateAssemblerFactory,
         EstateRepositoryFactory estateRepositoryFactory, AddressValidatorFactory addressValidatorFactory) {

      this.estateValidatorFactory = estateValidatorFactory;
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepositoryFactory = estateRepositoryFactory;
      this.addressValidatorFactory = addressValidatorFactory;
   }

   public void addEstate(EstateDto estateDto) throws InvalidEstateException {
      validateEstate(estateDto);
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      Estate estate = estateAssembler.assembleEstate(estateDto);

      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      estateRepository.addEstate(estate);
   }

   private void validateEstate(EstateDto estateDto) throws InvalidEstateException {
      EstateValidator estateValidator = estateValidatorFactory.getValidator();
      estateValidator.validate(estateDto);

      AddressValidator addressValidator = addressValidatorFactory.getValidator();
      addressValidator.validate(estateDto.getAddress());
   }

   public List<EstateDto> getAllEstates() {
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      List<Estate> estateList = estateRepository.getAllEstates();

      List<EstateDto> estatesDto = new ArrayList<EstateDto>();
      for (Estate estate : estateList) {
         estatesDto.add(estateAssembler.assembleEstateDto(estate));
      }

      return estatesDto;

   }

   public void editEstate(EstateDto estateDto) throws InvalidEstateException {
      EstateValidator estateValidator = estateValidatorFactory.getValidator();
      estateValidator.validate(estateDto);

      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();
      Estate estate = estateAssembler.assembleEstate(estateDto);

      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      estateRepository.editEstate(estate);
   }

}
