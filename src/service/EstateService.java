package service;

import customException.AddressFormatException;

import customException.EstateTypeException;
import customException.PriceException;
import domain.FactoryEstateAssembler;
import domain.Validator;
import domain.assembler.EstateAssembler;
import domain.estate.Estate;
import dto.EstateDto;
import repository.XmlEstateRepository;
import repository.EstateRepository;

public class EstateService 
{

    private FactoryEstateAssembler factoryEstateAssembler;
    private EstateRepository estateRepository;
    private Validator validator;
    
    public EstateService()
    {
        this.factoryEstateAssembler = new FactoryEstateAssembler();
        this.estateRepository = new XmlEstateRepository();
        this.validator = new Validator();
        
    }

    public void addEstate(EstateDto estateDto) throws EstateTypeException, AddressFormatException, PriceException
    {
        validator.validateEstateDto(estateDto);
        EstateAssembler estateAssembler = factoryEstateAssembler.getEstateAssembler(estateDto);
        Estate estate = estateAssembler.buildEstate(estateDto);
        estateRepository.addEstate(estate);
        
    }

}
