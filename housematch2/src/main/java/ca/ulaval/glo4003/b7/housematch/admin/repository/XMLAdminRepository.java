package ca.ulaval.glo4003.b7.housematch.admin.repository;

import java.io.File;
import java.util.List;

import javax.inject.Singleton;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Repository;

@Repository
@Singleton
public class XMLAdminRepository implements AdminRepository {

  @Override
  public boolean isAdmin(String email) {
    try {
      List<Node> list = readAdminXML().selectNodes("admins/admin");
      for (Node node : list) {
        if (node.selectSingleNode("email").getStringValue().equals(email))
          return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }

  private Document readAdminXML() throws DocumentException {
    File xml = new File("persistence/admins.xml");

    SAXReader reader = new SAXReader();
    return reader.read(xml);
  }

}
