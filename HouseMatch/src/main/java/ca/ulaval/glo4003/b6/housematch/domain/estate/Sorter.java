package ca.ulaval.glo4003.b6.housematch.domain.estate;

import java.util.List;

public interface Sorter {
  public void setEstates(List<Estate> estates);
  public List<Estate> getPriceAscendantSort();
  public List<Estate> getPriceDescendantSort();
  public List<Estate> getDateAscendantSort();
  public List<Estate> getDateDescendantSort();

}
