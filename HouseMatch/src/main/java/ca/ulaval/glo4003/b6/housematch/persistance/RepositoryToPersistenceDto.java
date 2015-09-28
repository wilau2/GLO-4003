package ca.ulaval.glo4003.b6.housematch.persistance;

import java.util.HashMap;

public interface RepositoryToPersistenceDto {

   HashMap<String, String> getAttributes();

   String getElementName();
}
