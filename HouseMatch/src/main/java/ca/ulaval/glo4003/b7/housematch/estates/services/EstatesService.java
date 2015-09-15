package ca.ulaval.glo4003.b7.housematch.estates.services;

import ca.ulaval.glo4003.b7.housematch.assembler.EstateAssembler;
import ca.ulaval.glo4003.b7.housematch.estates.EstateController;
import ca.ulaval.glo4003.b7.housematch.estates.dto.EstateDto;
import ca.ulaval.glo4003.b7.housematch.estates.dto.validators.EstateValidator;
import ca.ulaval.glo4003.b7.housematch.estates.exceptions.InvalidEstateException;

public class EstatesService {
	
	EstateValidator estateValidator;
	
   public EstatesService(EstateValidator estateValidator) {
		this.estateValidator = estateValidator;
	}

public void addEstate(String type, String address, Integer price){
	   
	   EstateController estateController = new EstateController();
	   
	   EstateAssembler estateAssembler = new EstateAssembler(estateValidator);
	   EstateDto estateDto;
	   try {
		   estateDto = estateAssembler.createDTO(type, address, price);
		   estateController.addEstate(estateDto);
	   } catch (Exception e) {
		   
	   }
   }

}
