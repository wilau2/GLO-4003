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

import ca.ulaval.glo4003.b6.housematch.domain.picture.ApprovalPictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Picture;
import ca.ulaval.glo4003.b6.housematch.domain.picture.Pictures;
import ca.ulaval.glo4003.b6.housematch.dto.InactivePictureDto;
import ca.ulaval.glo4003.b6.housematch.dto.assembler.InactivePictureAssembler;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDto;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence.FileEditor;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.converter.InactivePictureElementConverter;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.converter.RepositoryInactivePictureConverter;

public class InFileApprovalPictureRepository implements ApprovalPictureRepository {

   private FileEditor fileEditor;

   private PersistenceDtoFactory dtoFactory;

   private RepositoryInactivePictureConverter repositoryInactivePictureConverter;

   private InactivePictureAssembler pictureAssembler;

   private InactivePictureElementConverter pictureElementConverter;

   private final static String PATH_TO_XML = "persistence/inactivePictures.xml";

   private final static String PATH_TO_INACTIVE_PICTURE = "inactivePictures/inactivePicture";

   private static final String PATH_TO_UID = PATH_TO_INACTIVE_PICTURE + "/uid";

   @Inject
   public InFileApprovalPictureRepository(PersistenceDtoFactory persistenceDtoFactory, FileEditor fileEditor,
         InactivePictureAssembler inactivePictureAssembler,
         InactivePictureElementConverter inactivePictureElementConverter,
         RepositoryInactivePictureConverter repositoryInactivePictureConverter) {
      this.dtoFactory = persistenceDtoFactory;
      this.fileEditor = fileEditor;
      this.pictureAssembler = inactivePictureAssembler;
      this.pictureElementConverter = inactivePictureElementConverter;
      this.repositoryInactivePictureConverter = repositoryInactivePictureConverter;
   }

   @Override
   public void addPicture(Picture inactivePicture) throws UUIDAlreadyExistsException, CouldNotAccessDataException {
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
   public void deletePicture(String uid) throws CouldNotAccessDataException {
      try {
         Document inactivePicturesXML = readInactivePicturesXML();
         fileEditor.deleteExistingElementWithCorrespondingValue(inactivePicturesXML, PATH_TO_UID, uid);
         saveFile(inactivePicturesXML);
      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happenned trying to access the data", exception);
      }

   }

   @Override
   public Pictures getAllPictures() throws CouldNotAccessDataException {
      List<Picture> pictures = new ArrayList<Picture>();
      try {
         Document picturesXML = readInactivePicturesXML();
         List<Element> elementList = fileEditor.getAllElementsFromDocument(picturesXML, PATH_TO_INACTIVE_PICTURE);

         pictures = getDtoListFromElements(elementList, pictureAssembler, pictureElementConverter);

      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happenned trying to access the data", exception);
      }
      return new Pictures(pictures);
   }

   @Override
   public List<Picture> getPicturesByUids(List<String> uids) throws CouldNotAccessDataException {
      List<Picture> inactivePictures = new ArrayList<Picture>();
      for (Iterator<String> uidsIterator = uids.iterator(); uidsIterator.hasNext();) {
         inactivePictures.add(getPictureByUid(uidsIterator.next()));
      }
      return inactivePictures;

   }

   @Override
   public Picture getPictureByUid(String uid) throws CouldNotAccessDataException {
      Document inactivePicturesXML;
      try {
         inactivePicturesXML = readInactivePicturesXML();
      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happenned trying to access the data", exception);
      }
      return returnInactivePictureWithGivenUid(inactivePicturesXML, uid);

   }

   private List<Picture> getDtoListFromElements(List<Element> elementList,
         InactivePictureAssembler inactivePictureAssembler,
         InactivePictureElementConverter inactivePictureElementConverter) {
      List<Picture> inactivePictures = new ArrayList<Picture>();
      for (Element element : elementList) {
         InactivePictureDto convertedInactivePictureDto = inactivePictureElementConverter.convertToDto(element);
         Picture inactivePicture = inactivePictureAssembler.assembleInactivePicture(convertedInactivePictureDto);
         inactivePictures.add(inactivePicture);
      }
      return inactivePictures;
   }

   private Picture returnInactivePictureWithGivenUid(Document existingDocument, String uid) {
      HashMap<String, String> attributes = fileEditor.returnAttributesOfElementWithCorrespondingValue(existingDocument,
            PATH_TO_UID, uid);

      Picture inactivePicture = repositoryInactivePictureConverter.assembleInactivePictureFromAttributes(attributes);

      return inactivePicture;
   }

   private boolean idAlreadyExists(Document existingDocument, String Uid) {
      return fileEditor.elementWithCorrespondingValueExists(existingDocument, PATH_TO_UID, Uid);
   }

   private Document readInactivePicturesXML() throws DocumentException {
      return fileEditor.readXMLFile(PATH_TO_XML);
   }

   private void addInactivePictureToDocument(Document existingDocument, Picture inactivePicture) {

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

   @Override
   public void updatePictures(List<Picture> pictures) throws CouldNotAccessDataException, UUIDAlreadyExistsException {

      for (Iterator<Picture> picturesIterator = pictures.iterator(); picturesIterator.hasNext();) {
         Picture picture = picturesIterator.next();
         deletePicture(picture.getUid());
         addPicture(picture);
      }

   }

}