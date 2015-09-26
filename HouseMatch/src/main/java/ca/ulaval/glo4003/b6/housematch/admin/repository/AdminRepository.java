package ca.ulaval.glo4003.b6.housematch.admin.repository;

import ca.ulaval.glo4003.b6.housematch.admin.repository.exception.CouldNotAccesAdminDataException;

public interface AdminRepository {

   public boolean isAdmin(String email) throws CouldNotAccesAdminDataException;

}
