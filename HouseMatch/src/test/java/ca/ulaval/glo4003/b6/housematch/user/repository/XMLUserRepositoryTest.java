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
   private User user;

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
      configureUser();
   }

   @Test
   public void whenFindingByEmailShouldReadTheCorrectFile() throws DocumentException {
      // Given
      verify(editor).readXMLFile(correctPathToFile);

      // When
      repository.findByEmail(existingEmail);

      // Then
      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test
   public void whenFindingByEmailShouldLookIfUsersExists() {
      // Given

      // When
      repository.findByEmail(existingEmail);

      // Then
      verify(editor).elementWithCorrespondingValuesExists(usedDocument, correctPathToEmailValue, existingEmail);
   }

   @Test
   public void whenFindingByEmailShouldReturnAUserWithTheCorrectEmail() {
      // Given

      // When
      User returnedUser = repository.findByEmail(existingEmail);

      // Then
      assertEquals(returnedUser.getEmail(), existingEmail);
   }

   @Test
   public void whenFindingByEmailShouldReturnAUserWithTheCorrectPassword() {
      // Given

      // When
      User returnedUser = repository.findByEmail(existingEmail);

      // Then
      assertEquals(returnedUser.getPassword(), correctPassword);
   }

   @Test
   public void whenAddingUserShouldReadTheCorrectFile() throws DocumentException {
      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test
   public void whenAddingUserShouldLookIfUsersExists() {
      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(editor).elementWithCorrespondingValuesExists(usedDocument, correctPathToEmailValue, newEmail);
   }

   @Test
   public void whenAddingUserShouldAddNewUserToXMLWithCorrespondingMap() {
      // Given
      configureDifferentUser();
      HashMap<String, String> rightMap = returnTheCorrectMap();

      // When
      repository.add(user);

      // Then
      verify(editor).addNewElementToDocument(usedDocument, "user", rightMap);
   }

   @Test
   public void whenAddingUserShouldWriteToTheRightFile() throws IOException {
      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(editor).formatAndWriteDocument(usedDocument, correctPathToFile);
   }

   @Test(expected = UserNotFoundException.class)
   public void whenFindingByEmailShouldReturnExceptionIfEmailDoesNotExist() {
      // Given A new email

      // When
      repository.findByEmail(newEmail);

      // Then Exception is trown
   }

   @Test(expected = UserAlreadyExistsException.class)
   public void whenAddingUserShouldReturnExceptionIfEmailExist() {
      // Given An existing user

      // When
      repository.add(user);

      // Then Exception is trown
   }

   private HashMap<String, String> returnTheCorrectMap() {

      HashMap<String, String> rightMap = new HashMap<String, String>();
      rightMap.put("username", "username2");
      rightMap.put("firstName", "firstName2");
      rightMap.put("lastName", "lastName2");
      rightMap.put("phoneNumber", "phoneNumber2");
      rightMap.put("email", newEmail);
      rightMap.put("password", "newPassword");

      return rightMap;
   }

   private void configureUser() {
      given(user.getUsername()).willReturn("username");
      given(user.getFirstName()).willReturn("firstName");
      given(user.getLastName()).willReturn("lastName");
      given(user.getPhoneNumber()).willReturn("phoneNumber");
      given(user.getEmail()).willReturn(existingEmail);
      given(user.getPassword()).willReturn(correctPassword);
   }

   private void configureDifferentUser() {
      given(user.getUsername()).willReturn("username2");
      given(user.getFirstName()).willReturn("firstName2");
      given(user.getLastName()).willReturn("lastName2");
      given(user.getPhoneNumber()).willReturn("phoneNumber2");
      given(user.getEmail()).willReturn(newEmail);
      given(user.getPassword()).willReturn("newPassword");
   }

   private void configureEditor() throws DocumentException {
      given(editor.readXMLFile(correctPathToFile)).willReturn(usedDocument);
      given(editor.elementWithCorrespondingValuesExists(usedDocument, correctPathToEmailValue, existingEmail))
            .willReturn(true);

      HashMap<String, String> mapWithUserData = new HashMap<String, String>();
      mapWithUserData.put("username", "username");
      mapWithUserData.put("password", "firstName");
      mapWithUserData.put("email", "lastName");
      mapWithUserData.put("password", "phoneNumber");
      mapWithUserData.put("email", existingEmail);
      mapWithUserData.put("password", correctPassword);

      given(editor.returnAttributesOfElementWithCorrespondingValue(usedDocument, correctPathToEmailValue,
            existingEmail)).willReturn(mapWithUserData);
   }
}
