package ca.ulaval.glo4003.b6.housematch.estates.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import ca.ulaval.glo4003.b6.housematch.estates.domain.Estate;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.EstateAssembler;
import ca.ulaval.glo4003.b6.housematch.estates.domain.assembler.factory.EstateAssemblerFactory;
import ca.ulaval.glo4003.b6.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b6.housematch.estates.dto.validators.EstateValidatorFactory;
import ca.ulaval.glo4003.b6.housematch.estates.exceptions.InvalidEstateException;
import ca.ulaval.glo4003.b6.housematch.estates.repository.EstateRepository;

public class EstatesService {

   private EstateValidatorFactory estateValidatorFactory;

   private EstateRepository estateRepository;

   private EstateAssemblerFactory estateAssemblerFactory;

   @Autowired
   public EstatesService(EstateValidatorFactory estateValidatorFactory, EstateAssemblerFactory estateAssemblerFactory,
         EstateRepository estateRepository) {

      this.estateValidatorFactory = estateValidatorFactory;
      this.estateAssemblerFactory = estateAssemblerFactory;
      this.estateRepository = estateRepository;

   }

   public void addEstate(EstateDto estateDto) throws InvalidEstateException {
      EstateValidator estateValidator = estateValidatorFactory.getValidator();
      estateValidator.validate(estateDto);

      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();

      Estate estate = estateAssembler.assembleEstate(estateDto);

      estateRepository.addEstate(estate);
   }

   public Collection<EstateDto> getAllEstates() {
      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();
      Collection<EstateDto> estateDtoList = estateRepository.getAllEstatesDto();
      Collection<Estate> estateList = new ArrayList<>();
      for (Iterator<EstateDto> iterator = estateDtoList.iterator(); iterator.hasNext();) {
         estateList.add(estateAssembler.assembleEstate(iterator.next()));
      }

      Collection<EstateDto> estateDtoListToUi = new ArrayList<>();
      for (Iterator<Estate> iterator = estateList.iterator(); iterator.hasNext();) {
         estateDtoListToUi.add(estateAssembler.assembleEstateDto(iterator.next()));
      }

      return estateDtoListToUi;

   }

   public void editEstate(EstateDto estateDto) throws InvalidEstateException {
      EstateValidator estateValidator = estateValidatorFactory.getValidator();
      estateValidator.validate(estateDto);

      EstateAssembler estateAssembler = estateAssemblerFactory.createEstateAssembler();
      Estate estate = estateAssembler.assembleEstate(estateDto);

      estateRepository.editEstate(estate);
   }

}
