package ca.ulaval.glo4003.b6.housematch.persistence.picture;

import java.util.List;

import javax.inject.Inject;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePicture;
import ca.ulaval.glo4003.b6.housematch.domain.picture.InactivePictureRepository;
import ca.ulaval.glo4003.b6.housematch.domain.user.exceptions.UsernameAlreadyExistsException;
import ca.ulaval.glo4003.b6.housematch.persistence.PersistenceDtoFactory;
import ca.ulaval.glo4003.b6.housematch.persistence.FilePersistence.FileEditor;
import ca.ulaval.glo4003.b6.housematch.persistence.exceptions.CouldNotAccessDataException;
import ca.ulaval.glo4003.b6.housematch.persistence.picture.converter.RepositoryInactivePictureConverter;

public class InFileInactivePictureRepository implements InactivePictureRepository {

   private FileEditor fileEditor;

   private PersistenceDtoFactory dtoFactory;

   private RepositoryInactivePictureConverter converter;

   private final static String PATH_TO_XML = "persistence/inactivePictures.xml";

   @Inject
   public InFileInactivePictureRepository(PersistenceDtoFactory persistenceDtoFactory,
         RepositoryInactivePictureConverter repositoryUserConverter, FileEditor fileEditor) {
      this.dtoFactory = persistenceDtoFactory;
      this.converter = repositoryUserConverter;
      this.fileEditor = fileEditor;
   }

   @Override
   public void addInactivePicture(InactivePicture inactivePicture) {
      try {
         Document inactivePictures = readInactivePicturesXML();
         if (usernameAlreadyExists(inactivePictures, inactivePicture.getAddress(), inactivePicture.getName())) {
            throw new UsernameAlreadyExistsException("This username is already used");
         } else {
            addNewUserToDocument(inactivePictures, inactivePicture);
            saveFile(inactivePictures);
         }
      } catch (DocumentException exception) {
         throw new CouldNotAccessDataException("Something wrong happenned trying to access the data", exception);
      }

   }

   @Override
   public void deleteInactivePicture(InactivePicture inactivePicture) {
      // TODO Auto-generated method stub

   }

   @Override
   public List<InactivePicture> getAllInactivePicture() {
      // TODO Auto-generated method stub
      return null;
   }

   private Document readInactivePicturesXML() throws DocumentException {
      return fileEditor.readXMLFile(PATH_TO_XML);
   }

}
