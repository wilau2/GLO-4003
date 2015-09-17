package domain;

import domain.assembler.CottageAssembler;
import domain.assembler.EstateAssembler;
import dto.EstateDto;

public class FactoryEstateAssembler 
{
    
    public EstateAssembler getEstateAssembler(EstateDto estateDto)
    {

        
        if(estateDto.getType() == "COTTAGE")
        {
            return new CottageAssembler();
        }
        
        return null;
    }
}
