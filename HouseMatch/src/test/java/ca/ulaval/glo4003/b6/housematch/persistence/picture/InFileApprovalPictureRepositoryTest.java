package ca.ulaval.glo4003.b6.housematch.persistence.picture;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.InactivePictureAssembler;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDto;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence.FileEditor;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.converter.InactivePictureElementConverter;
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
   private InactivePictureElementConverter pictureElementConverter;

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

   @Test
   public void givenExistingUidWhenDeletePictureShouldDelegateSaving()
         throws UUIDAlreadyExistsException, CouldNotAccessDataException, IOException {
      // Given

      // When
      inFileApprovalPictureRepository.deletePicture(UID);

      // Then
      verify(fileEditor).formatAndWriteDocument(usedDocument, PATH_TO_XML);
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
      given(picture.getActive()).willReturn(true);
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
