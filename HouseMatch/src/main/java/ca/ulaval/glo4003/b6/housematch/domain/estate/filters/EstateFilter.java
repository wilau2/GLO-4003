package ca.ulaval.glo4003.b6.housematch.domain.estate.filters;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public interface EstateFilter {

   List<Estate> filter(List<Estate> estates, int minPrice, int maxPrice) throws InconsistentFilterParamaterException;

}
