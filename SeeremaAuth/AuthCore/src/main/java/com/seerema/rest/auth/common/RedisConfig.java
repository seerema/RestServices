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

package com.seerema.rest.auth.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.Protocol;

/**
 * Redis Configuration component
 * 
 */
@Configuration
public class RedisConfig {

  @Value("${spring.redis.host:" + Protocol.DEFAULT_HOST + "}")
  private String host;

  @Value("${spring.redis.port:" + Protocol.DEFAULT_PORT + "}")
  private int port;

  @Value("${spring.redis.password:}")
  private String password;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    RedisStandaloneConfiguration config =
        new RedisStandaloneConfiguration(host, port);
    config.setPassword(password);

    return new JedisConnectionFactory(config);
  }
}
