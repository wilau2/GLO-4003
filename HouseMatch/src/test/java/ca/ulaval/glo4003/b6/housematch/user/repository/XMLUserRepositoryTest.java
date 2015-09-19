package ca.ulaval.glo4003.b6.housematch.user.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.user.model.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public class XMLUserRepositoryTest {

   private String existingEmail = "an existing Email";

   private String correctPassword = "the corresponding password";

   private String newEmail = "a new Email";

   private String correctPathToFile = "persistence/users.xml";

   private String correctPathToEmailValue = "users/user/email";

   @Mock
   private XMLFileEditor editor;

   @Mock
   private Document usedDocument;

   @InjectMocks
   public XMLUserRepository repository;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      configureEditor();
   }

   @Test
   public void whenFindingByEmailShouldReadTheCorrectFile() throws DocumentException {
      repository.findByEmail(existingEmail);

      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test
   public void whenFindingByEmailShouldLookIfUsersExists() {
      repository.findByEmail(existingEmail);

      verify(editor).elementWithCorrespondingValuesExists(usedDocument, correctPathToEmailValue, existingEmail);
   }

   @Test
   public void whenFindingByEmailShouldReturnAUserWithTheCorrectEmail() {
      User returnedUser = repository.findByEmail(existingEmail);

      assertEquals(returnedUser.getEmail(), existingEmail);
   }

   @Test
   public void whenFindingByEmailShouldReturnAUserWithTheCorrectPassword() {
      User returnedUser = repository.findByEmail(existingEmail);

      assertEquals(returnedUser.getPassword(), correctPassword);
   }

   @Test
   public void whenAddingUserShouldReadTheCorrectFile() throws DocumentException {
      User user = new User();

      repository.add(user);

      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test
   public void whenAddingUserShouldLookIfUsersExists() {
      User user = new User();
      user.setEmail(newEmail);

      repository.add(user);

      verify(editor).elementWithCorrespondingValuesExists(usedDocument, correctPathToEmailValue, newEmail);
   }

   @Test
   public void whenAddingUserShouldAddNewUserToXMLWithCorrespondingMap() {
      User user = new User();
      user.setEmail(newEmail);
      user.setPassword("a password");

      repository.add(user);

      HashMap<String, String> rightMap = new HashMap<String, String>();
      rightMap.put("email", newEmail);
      rightMap.put("password", "a password");

      verify(editor).addNewElementToDocument(usedDocument, "user", rightMap);
   }

   @Test
   public void whenAddingUserShouldWriteToTheRightFile() throws IOException {
      User user = new User();
      user.setEmail(newEmail);

      repository.add(user);

      verify(editor).formatAndWriteDocument(usedDocument, correctPathToFile);
   }

   @Test(expected = UserNotFoundException.class)
   public void whenFindingByEmailShouldReturnExceptionIfEmailDoesNotExist() {
      repository.findByEmail(newEmail);
   }

   @Test(expected = UserAlreadyExistsException.class)
   public void whenAddingUserShouldReturnExceptionIfEmailExist() {
      User user = new User();
      user.setEmail(existingEmail);

      repository.add(user);
   }

   private void configureEditor() throws DocumentException {
      given(editor.readXMLFile(correctPathToFile)).willReturn(usedDocument);
      given(editor.elementWithCorrespondingValuesExists(usedDocument, correctPathToEmailValue, existingEmail))
            .willReturn(true);

      HashMap<String, String> mapWithUserData = new HashMap<String, String>();
      mapWithUserData.put("email", existingEmail);
      mapWithUserData.put("password", correctPassword);

      given(editor.returnAttributesOfElementWithCorrespondingValue(usedDocument, correctPathToEmailValue,
            existingEmail)).willReturn(mapWithUserData);
   }
}
