package ca.ulaval.glo4003.b7.housematch.user.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import ca.ulaval.glo4003.b7.housematch.user.model.User;
import ca.ulaval.glo4003.b7.housematch.user.repository.exception.UserNotFoundException;

public class XMLUserRepository implements UserRepository {

  @Override
  public User getByEmail(User user) {
    try {
      Document usersXML = readUsersXML();
      if (userAlreadyExists(usersXML, user.getEmail())) {
        throw new UserNotFoundException();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return user;
  }

  @Override
  public void add(User newUser) {
    try {
      Document usersXML = readUsersXML();
      if (!userAlreadyExists(usersXML, newUser.getEmail())) {
        addNewUserToDocument(usersXML, newUser);
        formatAndWriteDocument(usersXML);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private boolean userAlreadyExists(Document existingDocument, String email)
      throws DocumentException {
    List<Node> list = existingDocument.selectNodes("users/user");
    for (Node node : list) {
      if (node.selectSingleNode("email").getStringValue().equals(email))
        return true;
    }
    return false;
  }

  private Document readUsersXML() throws DocumentException {
    File xml = new File("persistence/users.xml");

    SAXReader reader = new SAXReader();
    return reader.read(xml);
  }

  private void addNewUserToDocument(Document existingDocument, User newUser) {
    Element usersElement = existingDocument.getRootElement();

    Element userElement = usersElement.addElement("user");
    userElement.addElement("email").addText(newUser.getEmail());
    userElement.addElement("password").addText(newUser.getPassword());
  }

  private void formatAndWriteDocument(Document existingDocument) throws IOException {
    OutputFormat format = OutputFormat.createPrettyPrint();

    XMLWriter writer = new XMLWriter(new FileWriter("persistence/users.xml"), format);
    writer.write(existingDocument);
    writer.close();
  }
}
