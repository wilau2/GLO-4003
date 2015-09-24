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

import ca.ulaval.glo4003.b6.housematch.persistance.RepositoryToPersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistance.XMLFileEditor;
import ca.ulaval.glo4003.b6.housematch.user.domain.User;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;

public class XMLUserRepositoryTest {

   private String existingUsername = "an existing Username";

   private String correctPassword = "the corresponding password";

   private String newUsername = "a new Username";

   private String correctPathToFile = "persistence/users.xml";

   private String correctPathToUsernameValue = "users/user/username";

   @Mock
   private User user;

   @Mock
   private XMLFileEditor editor;

   @Mock
   private Document usedDocument;

   @Mock
   private RepositoryToPersistenceDtoFactory dtoFactory;

   @Mock
   private RepositoryToPersistenceUserDto userDto;

   @InjectMocks
   public XMLUserRepository repository;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      configureEditor();
      configureUser();
      configureFactory();
   }

   @Test
   public void whenFindingByUsernameShouldReadTheCorrectFile() throws DocumentException {
      // Given

      // When
      repository.findByUsername(existingUsername);

      // Then
      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test
   public void whenFindingByUsernameShouldLookIfUsersExists() {
      // Given

      // When
      repository.findByUsername(existingUsername);

      // Then
      verify(editor).elementWithCorrespondingValueExists(usedDocument, correctPathToUsernameValue, existingUsername);
   }

   @Test
   public void whenFindingByUsernameShouldReturnAUserWithTheCorrectUsername() {
      // Given

      // When
      User returnedUser = repository.findByUsername(existingUsername);

      // Then
      assertEquals(returnedUser.getUsername(), existingUsername);
   }

   @Test
   public void whenFindingByUsernameShouldReturnAUserWithTheCorrectPassword() {
      // Given

      // When
      User returnedUser = repository.findByUsername(existingUsername);

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
      verify(editor).elementWithCorrespondingValueExists(usedDocument, correctPathToUsernameValue, newUsername);
   }

   @Test
   public void whenAddingUserShouldCreateNewDto() {
      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(dtoFactory).getRepositoryDto(user);
   }

   @Test
   public void whenAddingUserShouldAddNewUserToXMLWithDto() {
      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(editor).addNewElementToDocument(usedDocument, userDto);
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
   public void whenFindingByUsernameShouldReturnExceptionIfUsernameDoesNotExist() {
      // Given A new username

      // When
      repository.findByUsername(newUsername);

      // Then Exception is thrown
   }

   @Test(expected = UserAlreadyExistsException.class)
   public void whenAddingUserShouldReturnExceptionIfUsernameExist() {
      // Given An existing user

      // When
      repository.add(user);

      // Then Exception is thrown
   }

   private void configureFactory() {
      given(dtoFactory.getRepositoryDto(user)).willReturn(userDto);
   }

   private void configureUser() {
      given(user.getUsername()).willReturn(existingUsername);
      given(user.getPassword()).willReturn(correctPassword);
   }

   private void configureDifferentUser() {
      given(user.getUsername()).willReturn(newUsername);
      given(user.getPassword()).willReturn("newPassword");
   }

   private void configureEditor() throws DocumentException {
      given(editor.readXMLFile(correctPathToFile)).willReturn(usedDocument);
      given(editor.elementWithCorrespondingValueExists(usedDocument, correctPathToUsernameValue, existingUsername))
            .willReturn(true);

      HashMap<String, String> mapWithUserData = new HashMap<String, String>();
      mapWithUserData.put("username", existingUsername);
      mapWithUserData.put("password", correctPassword);

      given(editor.returnAttributesOfElementWithCorrespondingValue(usedDocument, correctPathToUsernameValue,
            existingUsername)).willReturn(mapWithUserData);
   }

}
