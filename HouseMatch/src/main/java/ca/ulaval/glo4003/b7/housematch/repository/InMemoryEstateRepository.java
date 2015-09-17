package ca.ulaval.glo4003.b7.housematch.repository;

import java.util.ArrayList;
import java.util.Collection;

import ca.ulaval.glo4003.b7.housematch.estates.Estate;
import ca.ulaval.glo4003.b7.housematch.repository.EstateRepository;

public class InMemoryEstateRepository implements EstateRepository {

    protected final Collection<Estate> estateList;
    
    public InMemoryEstateRepository() {
        estateList = new ArrayList<>();
    }
    
    @Override
    public void saveEstate(Estate estate) {
        estateList.add(estate);
    }
    
    @Override
    public Collection<Estate> getAllEstate() {
        return estateList;
    }
    
}