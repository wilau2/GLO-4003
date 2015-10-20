package ca.ulaval.glo4003.b6.housematch.persistance;

import java.util.HashMap;

public interface PersistenceDto {

   HashMap<String, String> getAttributes();

   String getElementName();
}
