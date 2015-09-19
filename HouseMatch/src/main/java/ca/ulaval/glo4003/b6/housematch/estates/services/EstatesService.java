package ca.ulaval.glo4003.b6.housematch.estates.services;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;

public class EstatesService {

   EstateValidator estateValidator;

   private EstateRepository estateRepository;

   private EstateAssemblerFactory estateAssemblerFactory;

   @Autowired
   public EstatesService(EstateValidator estateValidator, EstateAssemblerFactory estateAssemblerFactory,
         EstateRepository estateRepository) {

      this.estateValidator = estateValidator;
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepository = estateRepository;

   }

   public void addEstate(EstateDto estateDto) throws InvalidEstateException {
      estateValidator.validate(estateDto);

      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      Estate estate = estateAssembler.assembleEstate(estateDto);

      estateRepository.addEstate(estate);
   }

}
