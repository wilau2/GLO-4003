package ca.ulaval.glo4003.b6.housematch.persistance;

import java.util.HashMap;

public interface RepositoryToPersistenceDto {

   public HashMap<String, String> getAttributes();

   public String getElementName();
}
