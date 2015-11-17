package ca.ulaval.glo4003.b6.housematch.persistence.picture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePicture;
import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePictureRepository;
import ca.ulaval.glo4003.b6.housematch.dto.InactivePictureDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.InactivePictureAssembler;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDto;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence.FileEditor;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.converter.InactivePictureElementConverter;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.converter.RepositoryInactivePictureConverter;

public class InFileInactivePictureRepository implements InactivePictureRepository {

   private FileEditor fileEditor;

   private PersistenceDtoFactory dtoFactory;

   private RepositoryInactivePictureConverter repositoryInactivePictureConverter;

   private InactivePictureAssembler inactivePictureAssembler;

   private InactivePictureElementConverter inactivePictureElementConverter;

   private final static String PATH_TO_XML = "persistence/inactivePictures.xml";

   private final static String PATH_TO_INACTIVE_PICTURE = "inactivePictures/inactivePicture";

   private static final String PATH_TO_UID = PATH_TO_INACTIVE_PICTURE + "/Uid";

   @Inject
   public InFileInactivePictureRepository(PersistenceDtoFactory persistenceDtoFactory, FileEditor fileEditor,
         InactivePictureAssembler inactivePictureAssembler,
         InactivePictureElementConverter inactivePictureElementConverter,
         RepositoryInactivePictureConverter repositoryInactivePictureConverter) {
      this.dtoFactory = persistenceDtoFactory;
      this.fileEditor = fileEditor;
      this.inactivePictureAssembler = inactivePictureAssembler;
      this.inactivePictureElementConverter = inactivePictureElementConverter;
      this.repositoryInactivePictureConverter = repositoryInactivePictureConverter;
   }

   @Override
   public void addInactivePicture(InactivePicture inactivePicture)
         throws UUIDAlreadyExistsException, CouldNotAccessDataException {
      try {
         Document inactivePictures = readInactivePicturesXML();
         if (idAlreadyExists(inactivePictures, inactivePicture.getUid())) {
            throw new UUIDAlreadyExistsException("This UUID is already used");
         } else {
            addInactivePictureToDocument(inactivePictures, inactivePicture);
            saveFile(inactivePictures);
         }
      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happenned trying to access the data", exception);
      }

   }

   @Override
   public void deleteInactivePicture(String uid) throws CouldNotAccessDataException {
      try {
         Document inactivePicturesXML = readInactivePicturesXML();
         fileEditor.deleteExistingElementWithCorrespondingValue(inactivePicturesXML, PATH_TO_UID, uid);
         saveFile(inactivePicturesXML);
      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happenned trying to access the data", exception);
      }

   }

   @Override
   public List<InactivePicture> getAllInactivePicture() throws CouldNotAccessDataException {
      List<InactivePicture> inactivePictures = new ArrayList<InactivePicture>();
      try {
         Document inactivePicturesXML = readInactivePicturesXML();
         List<Element> elementList = fileEditor.getAllElementsFromDocument(inactivePicturesXML,
               PATH_TO_INACTIVE_PICTURE);

         inactivePictures = getDtoListFromElements(elementList, inactivePictureAssembler,
               inactivePictureElementConverter);

      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happenned trying to access the data", exception);
      }
      return inactivePictures;
   }

   @Override
   public List<InactivePicture> getInactivePicturesByUids(List<String> uids) throws CouldNotAccessDataException {
      List<InactivePicture> inactivePictures = new ArrayList<InactivePicture>();
      for (Iterator<String> uidsIterator = uids.iterator(); uidsIterator.hasNext();) {
         inactivePictures.add(getInactivePictureByUid(uidsIterator.next()));
      }
      return inactivePictures;

   }

   @Override
   public InactivePicture getInactivePictureByUid(String uid) throws CouldNotAccessDataException {
      Document inactivePicturesXML;
      try {
         inactivePicturesXML = readInactivePicturesXML();
      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happenned trying to access the data", exception);
      }
      return returnInactivePictureWithGivenUid(inactivePicturesXML, uid);

   }

   private List<InactivePicture> getDtoListFromElements(List<Element> elementList,
         InactivePictureAssembler inactivePictureAssembler,
         InactivePictureElementConverter inactivePictureElementConverter) {
      List<InactivePicture> inactivePictures = new ArrayList<InactivePicture>();
      for (Element element : elementList) {
         InactivePictureDto convertedInactivePictureDto = inactivePictureElementConverter.convertToDto(element);
         InactivePicture inactivePicture = inactivePictureAssembler
               .assembleInactivePicture(convertedInactivePictureDto);
         inactivePictures.add(inactivePicture);
      }
      return inactivePictures;
   }

   private InactivePicture returnInactivePictureWithGivenUid(Document existingDocument, String uid) {
      HashMap<String, String> attributes = fileEditor.returnAttributesOfElementWithCorrespondingValue(existingDocument,
            PATH_TO_UID, uid);

      InactivePicture inactivePicture = repositoryInactivePictureConverter
            .assembleInactivePictureFromAttributes(attributes);

      return inactivePicture;
   }

   private boolean idAlreadyExists(Document existingDocument, String Uid) {
      return fileEditor.elementWithCorrespondingValueExists(existingDocument, PATH_TO_UID, Uid);
   }

   private Document readInactivePicturesXML() throws DocumentException {
      return fileEditor.readXMLFile(PATH_TO_XML);
   }

   private void addInactivePictureToDocument(Document existingDocument, InactivePicture inactivePicture) {

      PersistenceDto inactivePictureDto = dtoFactory.getRepositoryDto(inactivePicture);

      fileEditor.addNewElementToDocument(existingDocument, inactivePictureDto);
   }

   private void saveFile(Document inactivePictureXML) throws DocumentException {
      try {
         fileEditor.formatAndWriteDocument(inactivePictureXML, PATH_TO_XML);
      } catch (IOException e) {
         throw new DocumentException("Could not access the specified file");
      }
   }

}
