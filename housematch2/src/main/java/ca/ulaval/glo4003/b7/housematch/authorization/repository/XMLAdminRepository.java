package ca.ulaval.glo4003.b7.housematch.authorization.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.inject.Singleton;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Repository;

@Repository
@Singleton
public class XMLAdminRepository implements AuthorizationRepository {

  @Override
  public void addAdmin(String email) {
    try {
      Document adminsXML = readAdminXML();
      if (!isAdmin(adminsXML, email)) {
        addAdminToDocument(adminsXML, email);
        formatAndWriteAdminDocument(adminsXML);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private Document readAdminXML() throws DocumentException {
    File xml = new File("persistence/admin.xml");

    SAXReader reader = new SAXReader();
    return reader.read(xml);
  }

  private void addAdminToDocument(Document existingDocument, String email) {
    Element usersElement = existingDocument.getRootElement();

    Element userElement = usersElement.addElement("admin");
    userElement.addElement("email").addText(email);
  }

  private void formatAndWriteAdminDocument(Document existingDocument) throws IOException {
    OutputFormat format = OutputFormat.createPrettyPrint();

    XMLWriter writer = new XMLWriter(new FileWriter("persistence/admins.xml"), format);
    writer.write(existingDocument);
    writer.close();
  }

  private boolean isAdmin(Document existingDocument, String email) throws DocumentException {
    List<Node> list = existingDocument.selectNodes("admins/admin");
    for (Node node : list) {
      if (node.selectSingleNode("email").getStringValue().equals(email))
        return true;
    }
    return false;
  }

}
