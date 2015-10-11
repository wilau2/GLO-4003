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
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.CouldNotAccessUserDataException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.user.repository.exception.UsernameAlreadyExistsException;

public class XMLUserRepositoryTest {

   private final String existingUsername = "an existing Username";

   private final String correctPassword = "the corresponding password";

   private final String newUsername = "a new Username";

   private final String correctPathToFile = "persistence/users.xml";

   private final String correctPathToUsernameValue = "users/user/username";

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
   private XMLUserRepository repository;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      configureEditor();
      configureUser();
      configureFactory();
   }

   @Test
   public void whenFindingByUsernameShouldReadTheCorrectFile()
         throws DocumentException, CouldNotAccessUserDataException, UserNotFoundException {

      // Given

      // When
      repository.getUser(existingUsername);

      // Then
      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test

   public void whenFindingByUsernameShouldLookIfUsersExists()
         throws CouldNotAccessUserDataException, UserNotFoundException {

      // Given

      // When
      repository.getUser(existingUsername);

      // Then
      verify(editor).elementWithCorrespondingValueExists(usedDocument, correctPathToUsernameValue, existingUsername);
   }

   @Test
   public void whenFindingByUsernameShouldReturnAUserWithTheCorrectUsername()
         throws CouldNotAccessUserDataException, UserNotFoundException {

      // Given

      // When
      User returnedUser = repository.getUser(existingUsername);

      // Then
      assertEquals(returnedUser.getUsername(), existingUsername);
   }

   @Test
   public void whenFindingByUsernameShouldReturnAUserWithTheCorrectPassword()
         throws CouldNotAccessUserDataException, UserNotFoundException {

      // Given

      // When
      User returnedUser = repository.getUser(existingUsername);

      // Then
      assertEquals(returnedUser.getPassword(), correctPassword);
   }

   @Test
   public void whenAddingUserShouldReadTheCorrectFile()

   throws DocumentException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {

      // Given
      configureDifferentUser();

      // When
      repository.addUser(user);

      // Then
      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test
   public void whenAddingUserShouldLookIfUsersExists()
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException {

      // Given
      configureDifferentUser();

      // When
      repository.addUser(user);

      // Then
      verify(editor).elementWithCorrespondingValueExists(usedDocument, correctPathToUsernameValue, newUsername);
   }

   @Test
   public void whenAddingUserShouldCreateNewDto()
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException {
      // Given
      configureDifferentUser();

      // When
      repository.addUser(user);

      // Then
      verify(dtoFactory).getRepositoryDto(user);
   }

   @Test
   public void whenAddingUserShouldAddNewUserToXMLWithDto()
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException {

      // Given
      configureDifferentUser();

      // When
      repository.addUser(user);

      // Then
      verify(editor).addNewElementToDocument(usedDocument, userDto);
   }

   @Test
   public void whenAddingUserShouldWriteToTheRightFile()
         throws IOException, UsernameAlreadyExistsException, CouldNotAccessUserDataException {

      // Given
      configureDifferentUser();

      // When
      repository.addUser(user);

      // Then
      verify(editor).formatAndWriteDocument(usedDocument, correctPathToFile);
   }

   @Test(expected = UserNotFoundException.class)
   public void whenFindingByUsernameShouldReturnExceptionIfUsernameDoesNotExist()
         throws CouldNotAccessUserDataException, UserNotFoundException {
      // Given A new username

      // When
      repository.getUser(newUsername);

      // Then Exception is thrown
   }

   @Test(expected = UsernameAlreadyExistsException.class)
   public void whenAddingUserShouldReturnExceptionIfUsernameExist()
         throws UsernameAlreadyExistsException, CouldNotAccessUserDataException {

      // Given An existing user

      // When
      repository.addUser(user);

      // Then Exception is thrown
   }

   @Test(expected = CouldNotAccessUserDataException.class)
   public void whenFindingByUsernameShouldReturnCouldNotAccessDataExceptionIfTheDocumentIsInvalid()
         throws CouldNotAccessUserDataException, UserNotFoundException, DocumentException {
      // Given
      given(editor.readXMLFile(correctPathToFile)).willThrow(new DocumentException());

      // When
      repository.getUser(existingUsername);

      // Then Exception is thrown
   }

   @Test(expected = CouldNotAccessUserDataException.class)
   public void whenAddingUserShouldReturnCouldNotAccessDataExceptionIfTheDocumentIsInvalid()
         throws CouldNotAccessUserDataException, UserNotFoundException, DocumentException,
         UsernameAlreadyExistsException {
      // Given
      given(editor.readXMLFile(correctPathToFile)).willThrow(new DocumentException());

      // When
      repository.addUser(user);

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
