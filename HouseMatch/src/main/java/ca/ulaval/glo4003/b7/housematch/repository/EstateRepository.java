package ca.ulaval.glo4003.b7.housematch.repository;

import java.util.Collection;

import ca.ulaval.glo4003.b7.housematch.estates.Estate;

public interface EstateRepository {
    
    public void saveEstate(Estate estate);
    
    public Collection<Estate> getAllEstate();
        
}