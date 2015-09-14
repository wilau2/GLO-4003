package org.ulaval.teamb6.housematch2.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.ulaval.teamb6.housematch2.config.RootConfiguration;

public class MessageSecurityWebApplicationInitializer
    extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { RootConfiguration.class };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected String[] getServletMappings() {
    // TODO Auto-generated method stub
    return null;
  }

}
