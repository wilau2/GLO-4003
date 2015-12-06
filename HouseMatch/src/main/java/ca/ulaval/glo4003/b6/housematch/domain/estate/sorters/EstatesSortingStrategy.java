package ca.ulaval.glo4003.b6.housematch.domain.estate.sorters;

import java.util.List;

import ca.ulaval.glo4003.b6.housematch.domain.estate.Estate;

public interface EstatesSortingStrategy {

   public void sort(List<Estate> estates);
}
