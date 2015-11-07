package ca.ulaval.glo4003.b6.housematch.services.admin;

import ca.ulaval.glo4003.b6.housematch.domain.user.UserRepository;

public interface UserRepositoryFactory {

   UserRepository newInstance();
}
