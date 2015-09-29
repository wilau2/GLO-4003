package ca.ulaval.glo4003.b6.housematch.estates.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Description;
import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.DescriptionAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.DescriptionAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.factories.EstatePersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.DescriptionValidator;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.DescriptionValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidDescriptionException;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.estates.repository.factory.EstateRepositoryFactory;

public class EstatesService {

   private EstateValidatorFactory estateValidatorFactory;

   private EstateRepositoryFactory estateRepositoryFactory;

   private EstateAssemblerFactory estateAssemblerFactory;

   private EstatePersistenceDtoFactory estatePersistenceDtoFactory;
   
   private DescriptionValidatorFactory descriptionValidatorFactory;
   
   private DescriptionAssemblerFactory descriptionAssemblerFactory;

   @Autowired
   public EstatesService(EstateValidatorFactory estateValidatorFactory, EstateAssemblerFactory estateAssemblerFactory,
          EstateRepositoryFactory estateRepositoryFactory, EstatePersistenceDtoFactory estatePersistenceDtoFactory, 
          DescriptionValidatorFactory descriptionValidatorFactory, DescriptionAssemblerFactory descriptionAssemblerFactory) {

      this.estateValidatorFactory = estateValidatorFactory;
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepositoryFactory = estateRepositoryFactory;
      this.estatePersistenceDtoFactory = estatePersistenceDtoFactory;
      this.descriptionValidatorFactory = descriptionValidatorFactory;
      this.descriptionAssemblerFactory = descriptionAssemblerFactory;
   }

   public void addEstate(EstateDto estateDto) throws InvalidEstateException {
      EstateValidator estateValidator = estateValidatorFactory.getValidator();
      estateValidator.validate(estateDto);

      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      Estate estate = estateAssembler.assembleEstate(estateDto);

      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory,
            estatePersistenceDtoFactory);
      estateRepository.addEstate(estate);
   }

   public List<EstateDto> getAllEstates() {
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory,
            estatePersistenceDtoFactory);
      List<Estate> estateList = estateRepository.getAllEstates();

      List<EstateDto> estatesDto = new ArrayList<EstateDto>();
      for (Estate estate : estateList) {
         estatesDto.add(estateAssembler.assembleEstateDto(estate));
      }

      return estatesDto;

   }

   //TODO a faire pour la 2e itération car c'est dans la story 7
   public void editEstate(EstateDto estateDto) throws InvalidEstateException {
      EstateValidator estateValidator = estateValidatorFactory.getValidator();
      estateValidator.validate(estateDto);

      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();
      Estate estate = estateAssembler.assembleEstate(estateDto);

      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory,
            estatePersistenceDtoFactory);
      estateRepository.editEstate(estate);
   }
   
   public void addDescription(DescriptionDto descriptionDto) throws InvalidDescriptionException{
      DescriptionValidator descriptionValidator = descriptionValidatorFactory.createValidator();
      descriptionValidator.validate(descriptionDto);
      
      String estateAddress = descriptionDto.getEstateID();
      
      DescriptionAssembler descriptionAssembler = descriptionAssemblerFactory.createDescriptionAssembler();
      Description description = descriptionAssembler.assembleDescription(descriptionDto);
      
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory,
            estatePersistenceDtoFactory);
      
      Estate estate = estateRepository.getEstate(estateAddress);
      estate.setDescription(description);
      
      estateRepository.editEstate(estate);
   }

}
