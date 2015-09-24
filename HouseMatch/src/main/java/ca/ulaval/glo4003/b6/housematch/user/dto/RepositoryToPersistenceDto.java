package ca.ulaval.glo4003.b6.housematch.user.dto;

import java.util.HashMap;

public interface RepositoryToPersistenceDto {

   HashMap<String, String> getAttributes();

   String getElementName();
}
