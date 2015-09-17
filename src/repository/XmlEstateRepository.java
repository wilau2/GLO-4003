package repository;

import domain.estate.Estate;
import persistence.XmlEstatePersistence;

public class XmlEstateRepository implements EstateRepository
{

    private XmlEstatePersistence xmlEstatePersistence;
    
    public XmlEstateRepository() 
    {
        this.xmlEstatePersistence = new XmlEstatePersistence();
    }
    
    @Override
    public void addEstate(Estate estate) 
    {
        xmlEstatePersistence.persistEstate(estate);       
    }

}
