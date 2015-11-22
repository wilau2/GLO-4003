package ca.ulaval.glo4003.b6.housematch.domain.estate.sorters;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public interface SortingStrategy {

   public List<Estate> sort(List<Estate> estates);
}
