package org.ulaval.teamb6.housematch2.repository;

import org.junit.Test;
import org.ulaval.teamb6.housematch2.domain.User;

public class XMLUserRepositoryTest {

  private XMLUserRepository repository;

  private User user;

  @Test
  public void simpleTest() {
    user = new User();
    repository = new XMLUserRepository();
    user.setEmail("an email adress");
    user.setPassword("a password");
    repository.add(user);
  }
}
