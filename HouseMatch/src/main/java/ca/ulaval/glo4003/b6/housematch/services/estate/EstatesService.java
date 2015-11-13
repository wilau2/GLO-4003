package ca.ulaval.glo4003.b6.housematch.services.estate;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateEditDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.services.estate.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.services.estate.validators.factory.EstateValidatorFactory;

public class EstatesService {

   private EstateValidatorFactory estateValidatorFactory;

   private EstateRepositoryFactory estateRepositoryFactory;

   private EstateAssemblerFactory estateAssemblerFactory;

   @Inject
   public EstatesService(EstateValidatorFactory estateValidatorFactory, EstateAssemblerFactory estateAssemblerFactory,
         EstateRepositoryFactory estateRepositoryFactory) {

      this.estateValidatorFactory = estateValidatorFactory;
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepositoryFactory = estateRepositoryFactory;
   }

   public void addEstate(EstateDto estateDto) throws InvalidEstateException, CouldNotAccessDataException {
      validateEstate(estateDto);
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      Estate estate = estateAssembler.assembleEstate(estateDto);

      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      estateRepository.addEstate(estate);
   }

   private void validateEstate(EstateDto estateDto) throws InvalidEstateException {
      EstateValidator estateValidator = estateValidatorFactory.getValidator();
      estateValidator.validate(estateDto);

   }
   
   public void editEstate(String address, EstateEditDto estateEditDto) throws EstateNotFoundException, CouldNotAccessDataException {
      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);
      Estate estate = estateRepository.getEstateByAddress(address);
      
      estate.edit(estateEditDto.getType(), estateEditDto.getPrice());
      estateRepository.updateEstate(estate);
   }

   public void editDescription(String address, DescriptionDto descriptionDto)
         throws InvalidEstateException, CouldNotAccessDataException, EstateNotFoundException {

      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();
      Description description = estateAssembler.assembleDescription(descriptionDto);

      EstateRepository estateRepository = estateRepositoryFactory.newInstance(estateAssemblerFactory);

      Estate estate = estateRepository.getEstateByAddress(address);
      estate.editDescription(description);
      estateRepository.updateEstate(estate);

   }
}
