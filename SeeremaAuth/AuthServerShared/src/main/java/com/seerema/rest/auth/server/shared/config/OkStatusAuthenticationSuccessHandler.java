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

package com.seerema.rest.auth.server.shared.config;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Authentication Success Handler
 * 
 */
public class OkStatusAuthenticationSuccessHandler
    implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest req,
      HttpServletResponse resp, Authentication auth)
      throws IOException, ServletException {
    // Set list of roles
    if (auth != null)
      resp.getWriter()
          .print("{\"roles\":[\"" +
              auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                  .collect(Collectors.joining("\",\"")) +
              "\"]}");

    // set our response to OK status
    resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    resp.setStatus(HttpStatus.OK.value());
  }

}
