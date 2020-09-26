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

package com.seerema.shared.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AjpNioProtocol;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * Abstract REST Web App Configuration
 * 
 */
public abstract class AbstractBaseRestAjpConfig {

  @Value("${tomcat.ajp.port:8009}")
  int ajpPort;

  @Value("${tomcat.ajp.enabled:false}")
  boolean tomcatAjpEnabled;

  @Autowired
  private Logger _log;

  @Bean
  public WebServerFactoryCustomizer<TomcatServletWebServerFactory>
      initAjpConnector() {
    return new WebServerFactoryCustomizer<TomcatServletWebServerFactory>() {

      @Override
      public void customize(TomcatServletWebServerFactory factory) {
        if (tomcatAjpEnabled) {
          Connector ajpConnector = new Connector("AJP/1.3");

          // Disable secret since Web App running behind reversed proxy
          AjpNioProtocol protocol =
              (AjpNioProtocol) ajpConnector.getProtocolHandler();
          protocol.setSecretRequired(false);
          try {
            protocol.setAddress(
                InetAddress.getByAddress(new byte[] { 0, 0, 0, 0 }));
          } catch (UnknownHostException e) {
            _log.error("Unable bind AJP port to 0.0.0.0 interfase - " +
                e.getMessage());
          }

          ajpConnector.setPort(ajpPort);
          ajpConnector.setSecure(false);
          ajpConnector.setAllowTrace(false);
          ajpConnector.setScheme("http");

          factory.addAdditionalTomcatConnectors(ajpConnector);
        }
      }
    };
  }

}
