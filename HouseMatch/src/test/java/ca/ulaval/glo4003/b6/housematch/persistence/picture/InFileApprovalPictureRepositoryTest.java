package ca.ulaval.glo4003.b6.housematch.persistence.picture;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.dto.InformationPictureDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.InactivePictureAssembler;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDto;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence.FileEditor;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.converter.PictureElementConverter;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.converter.RepositoryInactivePictureConverter;

public class InFileApprovalPictureRepositoryTest {

   private final String ADDRESS = "address";

   private final String UID = "uid";

   private final String NAME = "a new Username";

   private final String ACTIVE = "true";

   private final static String PATH_TO_XML = "persistence/inactivePictures.xml";

   private final static String PATH_TO_INACTIVE_PICTURE = "inactivePictures/inactivePicture";

   private static final String PATH_TO_UID = PATH_TO_INACTIVE_PICTURE + "/uid";

   @Mock
   private FileEditor fileEditor;

   @Mock
   private PersistenceDtoFactory dtoFactory;

   @Mock
   private RepositoryInactivePictureConverter repositoryInactivePictureConverter;

   @Mock
   private InactivePictureAssembler pictureAssembler;

   @Mock
   private PictureElementConverter pictureElementConverter;

   @Mock
   Picture picture;

   @Mock
   PersistenceDto persistanceDto;

   @Mock
   Document usedDocument;

   @InjectMocks
   InFileApprovalPictureRepository inFileApprovalPictureRepository;

   private final String NEW_UID = "newUID";

   @Mock
   private List<Picture> pictures;

   @Mock
   private Iterator<Picture> iterator;

   @Mock
   private List<String> uids;

   @Mock
   private Iterator<String> iteratorStr;

   @Mock
   private InformationPictureDto inactivePictureDto;

   @Mock
   private Element element;

   @Before
   public void setup() throws DocumentException {
      MockitoAnnotations.initMocks(this);
      configureEditor();
      configurePicture();
      configureFactory();
   }

   @Test
   public void givenNewUidWhenAddPictureShouldDelegateSaving()
         throws UUIDAlreadyExistsException, CouldNotAccessDataException, IOException {
      // Given
      given(picture.getUid()).willReturn(NEW_UID);

      // When
      inFileApprovalPictureRepository.addPicture(picture);

      // Then
      verify(fileEditor).formatAndWriteDocument(usedDocument, PATH_TO_XML);
   }

   @Test(expected = UUIDAlreadyExistsException.class)
   public void givenNewUidWhenAddingPicutreAlreadyExistingShouldThrowExpcetion()
         throws UUIDAlreadyExistsException, CouldNotAccessDataException {
      // Given
      when(picture.getUid()).thenReturn(UID);
      when(fileEditor.elementWithCorrespondingValueExists(usedDocument, PATH_TO_UID, UID)).thenReturn(true);

      // When
      inFileApprovalPictureRepository.addPicture(picture);

      // Then an UUID already exists exception is thrown
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void givenAddingAPictureWhenFileEditorThrowDocumentShouldThrowException()
         throws IOException, UUIDAlreadyExistsException, CouldNotAccessDataException {
      // Given
      given(picture.getUid()).willReturn(NEW_UID);
      doThrow(new IOException()).when(fileEditor).formatAndWriteDocument(usedDocument, PATH_TO_XML);

      // When
      inFileApprovalPictureRepository.addPicture(picture);

      // Then a document exception in thrown
   }

   @Test
   public void givenExistingUidWhenDeletePictureShouldDelegateSaving()
         throws UUIDAlreadyExistsException, CouldNotAccessDataException, IOException {
      // Given

      // When
      inFileApprovalPictureRepository.deletePicture(UID);

      // Then
      verify(fileEditor).formatAndWriteDocument(usedDocument, PATH_TO_XML);
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void givenAnDocumentExceptionWhenDeletingPictureShouldThrowException()
         throws CouldNotAccessDataException, DocumentException, IOException {
      // Given
      given(fileEditor.readXMLFile(PATH_TO_XML)).willReturn(usedDocument);
      doThrow(new IOException()).when(fileEditor).formatAndWriteDocument(usedDocument, PATH_TO_XML);

      // When
      inFileApprovalPictureRepository.deletePicture(UID);

      // Then a could not access data exception is thrown
   }

   @Test
   public void givenExistingUidWhenUpdatePicturesShouldDelegateSaving()
         throws UUIDAlreadyExistsException, CouldNotAccessDataException, IOException {
      // Given
      congigurePictureListWithOneElement();
      given(picture.getUid()).willReturn(UID, NEW_UID);
      // When
      inFileApprovalPictureRepository.updatePictures(pictures);

      // Then
      verify(fileEditor, times(2)).formatAndWriteDocument(usedDocument, PATH_TO_XML);
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void givenAFileEditorExceptionWhenGettingAllPicturesShouldThrowException()
         throws CouldNotAccessDataException, DocumentException {
      // Given
      given(fileEditor.readXMLFile(PATH_TO_XML)).willThrow(new DocumentException());

      // When
      inFileApprovalPictureRepository.getAllPictures();

      // Then a could not access data exception is thrown
   }

   @Test
   public void givenAListOfElementWhenGettingAllPicturesShouldCallMultipleTimesTheAssembler()
         throws CouldNotAccessDataException, DocumentException {
      // Given
      int numberOfElement = 3;
      configureGetAllPictures(numberOfElement);

      // When
      inFileApprovalPictureRepository.getAllPictures();

      // Then
      verify(pictureElementConverter, times(numberOfElement)).convertToDto(element);
      verify(pictureAssembler, times(numberOfElement)).assembleInactivePicture(inactivePictureDto);
   }

   @Test(expected = CouldNotAccessDataException.class)
   public void givenFileEditorExcpetionWhenGettingPictureByUUIDShouldThrowException()
         throws CouldNotAccessDataException, DocumentException {
      // Given
      given(fileEditor.readXMLFile(PATH_TO_XML)).willThrow(new DocumentException());

      // When
      inFileApprovalPictureRepository.getPictureByUid(UID);

      // Then a could not access data exception
   }

   private void configureGetAllPictures(int numberOfElement) throws DocumentException {
      List<Element> elements = new ArrayList<Element>();
      for (int i = 0; i < numberOfElement; i++) {
         elements.add(element);
      }
      given(fileEditor.readXMLFile(PATH_TO_XML)).willReturn(usedDocument);
      given(fileEditor.getAllElementsFromDocument(usedDocument, PATH_TO_INACTIVE_PICTURE)).willReturn(elements);
      given(pictureElementConverter.convertToDto(element)).willReturn(inactivePictureDto);
   }

   @Test
   public void givenOnePictureWhenGetAllPicturesShouldDelegateGettingAllElementToFileEditor()
         throws CouldNotAccessDataException {
      // Given

      // When
      inFileApprovalPictureRepository.getAllPictures();

      // Then
      verify(fileEditor).getAllElementsFromDocument(usedDocument, PATH_TO_INACTIVE_PICTURE);

   }

   @Test
   public void givenOnePictureWhenGetPictureByUidShouldDelegateGettingAttributesOfCorrespondingValue()
         throws CouldNotAccessDataException {
      // Given

      // When
      inFileApprovalPictureRepository.getPictureByUid(UID);

      // Then
      verify(fileEditor).returnAttributesOfElementWithCorrespondingValue(usedDocument, PATH_TO_UID, UID);

   }

   @Test
   public void givenOnePictureWhenGetAllPicturesByUidsShouldDelegateGettingAttributesOfCorrespondingValue()
         throws CouldNotAccessDataException {
      // Given
      congigureUidListWithOneElement();
      // When
      inFileApprovalPictureRepository.getPicturesByUids(uids);

      // Then
      verify(fileEditor).returnAttributesOfElementWithCorrespondingValue(usedDocument, PATH_TO_UID, UID);
   }

   private void congigurePictureListWithOneElement() {
      when(pictures.iterator()).thenReturn(iterator);
      when(iterator.hasNext()).thenReturn(true, false);
      when(iterator.next()).thenReturn(picture);
   }

   private void congigureUidListWithOneElement() {
      when(uids.iterator()).thenReturn(iteratorStr);
      when(iteratorStr.hasNext()).thenReturn(true, false);
      when(iteratorStr.next()).thenReturn(UID);
   }

   private void configureFactory() {
      given(dtoFactory.getRepositoryDto(picture)).willReturn(persistanceDto);
   }

   private void configurePicture() {
      given(picture.isActive()).willReturn(true);
      given(picture.getAddress()).willReturn(ADDRESS);
      given(picture.getName()).willReturn(NAME);
      given(picture.getUid()).willReturn(UID);
   }

   private void configureEditor() throws DocumentException {
      given(fileEditor.readXMLFile(PATH_TO_XML)).willReturn(usedDocument);
      given(fileEditor.elementWithCorrespondingValueExists(usedDocument, PATH_TO_UID, UID)).willReturn(true);

      HashMap<String, String> mapWithPictureData = new HashMap<String, String>();
      mapWithPictureData.put("address", ADDRESS);
      mapWithPictureData.put("name", NAME);
      mapWithPictureData.put("uid", UID);
      mapWithPictureData.put("active", ACTIVE);

      given(fileEditor.returnAttributesOfElementWithCorrespondingValue(usedDocument, PATH_TO_UID, UID))
            .willReturn(mapWithPictureData);

   }

}
