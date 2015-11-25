package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.List;

public interface EstateFilter {

   List<Estate> filter(List<Estate> estates, int minPrice, int maxPrice) throws InconsistentFilterParamaterException;

}
