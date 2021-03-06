package ca.ulaval.glo4003.b6.housematch.persistence.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UserNotFoundException;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence.FileEditor;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.user.converter.RepositoryUserConverter;

public class XMLUserRepositoryTest {

   private final String existingUsername = "an existing Username";

   private final String correctPassword = "the corresponding password";

   private final String newUsername = "a new Username";

   private final String correctPathToFile = "persistence/users.xml";

   private final String correctPathToUsernameValue = "users/user/username";

   @Mock
   private User user;

   @Mock
   private FileEditor editor;

   @Mock
   private Document usedDocument;

   @Mock
   private PersistenceDtoFactory dtoFactory;

   @Mock
   private UserPersistenceDto userDto;

   @Mock
   private RepositoryUserConverter assembler;

   @InjectMocks
   private XMLUserRepository repository;

   @Mock
   private Element userElement;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      configureEditor();
      configureUser();
      configureFactory();
   }

   @Test
   public void whenFindingByUsernameShouldReadTheCorrectFile()
         throws DocumentException, CouldNotAccessDataException, UserNotFoundException {

      // Given

      // When
      repository.getUser(existingUsername);

      // Then
      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test
   public void whenFindingByUsernameShouldLookIfUsersExists()
         throws CouldNotAccessDataException, UserNotFoundException {

      // Given

      // When
      repository.getUser(existingUsername);

      // Then
      verify(editor).elementWithCorrespondingValueExists(usedDocument, correctPathToUsernameValue, existingUsername);
   }

   @Test
   public void whenFindingByUsernameShouldReturnAUserWithTheCorrectUsername()
         throws CouldNotAccessDataException, UserNotFoundException {

      // Given

      // When
      User returnedUser = repository.getUser(existingUsername);

      // Then
      assertEquals(returnedUser.getUsername(), existingUsername);
   }

   @Test
   public void whenFindingByUsernameShouldReturnAUserWithTheCorrectPassword()
         throws CouldNotAccessDataException, UserNotFoundException {

      // Given

      // When
      User returnedUser = repository.getUser(existingUsername);

      // Then
      assertEquals(returnedUser.getPassword(), correctPassword);
   }

   @Test
   public void whenAddingUserShouldReadTheCorrectFile()

   throws DocumentException, UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(editor).readXMLFile(correctPathToFile);
   }

   @Test
   public void whenAddingUserShouldLookIfUsersExists()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(editor).elementWithCorrespondingValueExists(usedDocument, correctPathToUsernameValue, newUsername);
   }

   @Test
   public void whenAddingUserShouldCreateNewDto() throws UsernameAlreadyExistsException, CouldNotAccessDataException {
      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(dtoFactory).getRepositoryDto(user);
   }

   @Test
   public void whenAddingUserShouldAddNewUserToXMLWithDto()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(editor).addNewElementToDocument(usedDocument, userDto);
   }

   @Test
   public void whenAddingUserShouldWriteToTheRightFile()
         throws IOException, UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given
      configureDifferentUser();

      // When
      repository.add(user);

      // Then
      verify(editor).formatAndWriteDocument(usedDocument, correctPathToFile);
   }

   @Test
   public void whenUpdatingAUserShouldCallTheDeleteMethodWithTheRightArguments()
         throws IOException, UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given

      // When
      repository.update(user);

      // Then
      verify(editor).deleteExistingElementWithCorrespondingValue(usedDocument, correctPathToUsernameValue,
            existingUsername);
   }

   @Test
   public void whenUpdatingAUserShouldCallAddUserWithTheRightArguments()
         throws IOException, UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given

      // When
      repository.update(user);

      // Then
      verify(editor).addNewElementToDocument(usedDocument, userDto);
   }

   @Test(expected = UserNotFoundException.class)
   public void whenFindingByUsernameShouldReturnExceptionIfUsernameDoesNotExist()
         throws CouldNotAccessDataException, UserNotFoundException {
      // Given A new username

      // When
      repository.getUser(newUsername);

      // Then Exception is thrown
   }

   @Test(expected = UsernameAlreadyExistsException.class)
   public void whenAddingUserShouldReturnExceptionIfUsernameExist()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException {

      // Given An existing user

      // When
      repository.add(user);

      // Then Exception is thrown
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void whenFindingByUsernameShouldReturnCouldNotAccessDataExceptionIfTheDocumentIsInvalid()
         throws CouldNotAccessDataException, UserNotFoundException, DocumentException {
      // Given
      given(editor.readXMLFile(correctPathToFile)).willThrow(new DocumentException());

      // When
      repository.getUser(existingUsername);

      // Then Exception is thrown
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void whenUpdatingShouldReturnCouldNotAccessDataExceptionIfTheDocumentIsInvalid()
         throws CouldNotAccessDataException, UserNotFoundException, DocumentException {
      // Given
      given(editor.readXMLFile(correctPathToFile)).willThrow(new DocumentException());

      // When
      repository.update(user);

      // Then Exception is thrown
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void whenAddingUserShouldReturnCouldNotAccessDataExceptionIfTheDocumentIsInvalid()
         throws CouldNotAccessDataException, UserNotFoundException, DocumentException, UsernameAlreadyExistsException {
      // Given
      given(editor.readXMLFile(correctPathToFile)).willThrow(new DocumentException());

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
      mapWithUserData.put("firstName", "firstName");
      mapWithUserData.put("lastName", "lastName");
      mapWithUserData.put("role", "role");
      mapWithUserData.put("phoneNumber", "phoneNumber");
      mapWithUserData.put("email", "email");
      mapWithUserData.put("isActive", "true");

      given(editor.returnAttributesOfElementWithCorrespondingValue(usedDocument, correctPathToUsernameValue,
            existingUsername)).willReturn(mapWithUserData);

      given(assembler.assembleUserFromAttributes(mapWithUserData)).willReturn(user);
      given(assembler.assembleUserFromElement(userElement)).willReturn(user);
   }

   @Test
   public void whenGettingAllUserInSystemShouldGetAllElementsFromXmlDocument() throws DocumentException {
      // Given no changes

      // When
      repository.getAllUsers();

      // Then
      verify(editor, times(1)).readXMLFile("persistence/users.xml");
      verify(editor, times(1)).getAllElementsFromDocument(usedDocument, "users/user");
   }

   @Test
   public void whenGettingAllUserInSystemShouldAssembleAllDocumentReturnedByFileEditor() throws DocumentException {
      // Given
      int numberOfDocumentReturned = 3;
      configureFileEditorGetAllUserElement(numberOfDocumentReturned);

      // When
      repository.getAllUsers();

      // Then
      verify(assembler, times(numberOfDocumentReturned)).assembleUserFromElement(userElement);
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void updatingUserWhenSavingFileThrowIOExceptionShouldThrowException()
         throws UsernameAlreadyExistsException, CouldNotAccessDataException, IOException {
      // Given
      doThrow(new IOException("")).when(editor).formatAndWriteDocument(any(), eq("persistence/users.xml"));

      // When
      repository.update(user);

      // Then a Could not access data exception is thrown
   }

   private void configureFileEditorGetAllUserElement(int numberOfDocumentReturned) {
      List<Element> listOfUserELement = new ArrayList<Element>();
      for (int i = 0; i < numberOfDocumentReturned; i++) {
         listOfUserELement.add(userElement);
      }
      given(editor.getAllElementsFromDocument(usedDocument, "users/user")).willReturn(listOfUserELement);
   }

}
