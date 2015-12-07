package ca.ulaval.glo4003.b6.housematch.services.estate;

import javax.inject.Inject;

import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.DescriptionCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.EstateCorruptionVerificator;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidDescriptionFieldException;
import ca.ulaval.glo4003.b6.housematch.anticorruption.estate.exceptions.InvalidEstateFieldException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.ChangeVerificator;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Description;
import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;
import ca.ulaval.glo4003.b6.housematch.domain.estate.EstateRepository;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateAlreadyBoughtException;
import ca.ulaval.glo4003.b6.housematch.domain.estate.exceptions.EstateNotFoundException;
import ca.ulaval.glo4003.b6.housematch.dto.DescriptionDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.dto.EstateEditDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.services.estate.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.services.estate.validators.EstateValidator;

public class EstatesService {

   private EstateValidator estateValidator;

   private EstateRepository estateRepository;

   private EstateAssemblerFactory estateAssemblerFactory;

   private ChangeVerificator changeVerificator;

   private EstateCorruptionVerificator estateCorruptionVerificator;

   private DescriptionCorruptionVerificator descriptionCorruptionVerificator;

   @Inject
   public EstatesService(EstateCorruptionVerificator estateCorruptionVerificator,
         DescriptionCorruptionVerificator descriptionCorruptionVerificator, EstateValidator estateValidator,
         EstateAssemblerFactory estateAssemblerFactory, EstateRepository estateRepository,
         ChangeVerificator changeVerificator) {

      this.estateCorruptionVerificator = estateCorruptionVerificator;
      this.descriptionCorruptionVerificator = descriptionCorruptionVerificator;
      this.estateValidator = estateValidator;
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepository = estateRepository;
      this.changeVerificator = changeVerificator;
   }

   public void addEstate(EstateDto estateDto)
         throws InvalidEstateException, CouldNotAccessDataException, InvalidEstateFieldException {

      estateCorruptionVerificator.validateEstateCorruption(estateDto);

      validateEstate(estateDto);
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      Estate estate = estateAssembler.assembleEstate(estateDto);

      estateRepository.addEstate(estate);
   }

   private void validateEstate(EstateDto estateDto) throws InvalidEstateException {
      estateValidator.validate(estateDto);
   }

   public void editEstate(String address, EstateEditDto estateEditDto)
         throws EstateNotFoundException, CouldNotAccessDataException, InvalidEstateFieldException {
      estateCorruptionVerificator.validateEstateEditCorruption(estateEditDto);

      Estate estate = estateRepository.getEstateByAddress(address);

      estate.editType(estateEditDto.getType());
      estate.editPrice(estateEditDto.getPrice());

      estateRepository.updateEstate(estate);
   }

   public void editDescription(String address, DescriptionDto descriptionDto)
         throws CouldNotAccessDataException, EstateNotFoundException, InvalidDescriptionFieldException {
      descriptionCorruptionVerificator.validateDescriptionCorruption(descriptionDto);
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();
      Description description = estateAssembler.assembleDescription(descriptionDto);

      Estate estate = estateRepository.getEstateByAddress(address);
      estate.editDescription(description, changeVerificator);

      estateRepository.updateEstate(estate);
   }

   public void buyEstate(String address)
         throws EstateNotFoundException, CouldNotAccessDataException, EstateAlreadyBoughtException {

      Estate estate = estateRepository.getEstateByAddress(address);

      estate.buy();

      estateRepository.updateEstate(estate);
   }
}
