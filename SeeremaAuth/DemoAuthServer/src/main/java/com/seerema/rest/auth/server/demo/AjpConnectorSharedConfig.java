/*
 * Seerema Business Solutions - http://www.seerema.com/
 * 
 * Copyright 2020 IvaLab Inc. and by respective contributors (see below).
 * 
 * Released under the LGPL v3 or higher
 * See http://www.gnu.org/licenses/lgpl.txt
 *
 * Contributors:
 * 
 */

package com.seerema.rest.auth.server.demo;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AjpNioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot Launcher
 * 
 */
public class AjpConnectorSharedConfig {

  @Value("${tomcat.ajp.port:8009}")
  int ajpPort;

  @Value("${tomcat.ajp.enabled:false}")
  boolean tomcatAjpEnabled;

  @Bean
  public TomcatServletWebServerFactory servletContainer() {
    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();

    if (tomcatAjpEnabled) {
      Connector ajpConnector = new Connector("AJP/1.3");

      // Disable secret since Web App running behind reversed proxy
      AjpNioProtocol protocol =
          (AjpNioProtocol) ajpConnector.getProtocolHandler();
      protocol.setSecretRequired(false);

      ajpConnector.setPort(ajpPort);
      ajpConnector.setSecure(false);
      ajpConnector.setAllowTrace(false);
      ajpConnector.setScheme("http");
      tomcat.addAdditionalTomcatConnectors(ajpConnector);
    }

    return tomcat;
  }

}
