/*
 * Copyright Christopher Schmidt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tagger.client.util

import com.sun.jersey.api.client.WebResource
import javax.ws.rs.core.{UriBuilder, MediaType}
import com.sun.jersey.client.apache.config.{DefaultApacheHttpClientConfig, ApacheHttpClientConfig}
import com.sun.jersey.api.client.config.ClientConfig
import com.tagger.Configuration
import com.sun.jersey.client.apache.ApacheHttpClient
import com.tagger.security.LoginUser

/**
 * Created by IntelliJ IDEA.
 * User: FaKod
 * Date: 26.01.2010
 * Time: 06:42:11
 */

/**
 * base class for all client services
 */
abstract class ClientServiceBase(val user: LoginUser) {

  /**
   * creating the base URIs
   */
  protected val baseURI = Configuration.getBaseURI
  protected val servicePath: String

  private val uri = UriBuilder.fromUri(baseURI).path(servicePath).build()

  /**
   * creating the configuration for apache client
   * basic authentication and timeouts
   */
  private val config = new DefaultApacheHttpClientConfig()

  config.getProperties().put(ApacheHttpClientConfig.PROPERTY_PREEMPTIVE_AUTHENTICATION, java.lang.Boolean.TRUE)
  config.getProperties().put(ApacheHttpClientConfig.PROPERTY_HANDLE_COOKIES, java.lang.Boolean.TRUE)
  config.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, Configuration.getConnectTimeout.asInstanceOf[Object])
  config.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, Configuration.getReadTimeout.asInstanceOf[Object])

  config.getState().setCredentials(null, null, -1, user.username, user.password)

  /**
   * creating client with given configuration
   */
  private val client = ApacheHttpClient.create(config)
  client.addFilter(new com.sun.jersey.api.client.filter.LoggingFilter())

  private val thisResource = client.resource(uri)

  /**
   * the is the web resource to use
   */
  protected def resource = thisResource

  /**
   * default media type
   */
  final protected def MT = MediaType.APPLICATION_XML

  /**
   * executes a rest closure
   */
  protected def doRest[A](path: String)(f: (WebResource#Builder) => A): A = {
    if (path != null)
      f(resource.path(path).accept(MT).`type`(MT)).asInstanceOf[A]
    else
      f(resource.accept(MT).`type`(MT)).asInstanceOf[A]
  }

  /**
   * helper for returning the String after the lase "/" in String s
   */
  protected def getID(s: String): String = s.substring(s.lastIndexOf("/") + 1)

  /**
   * defining REST call DSL
   */
  protected def rest[A](body: (WebResource#Builder) => A): DoRestWithPath[A] = new DoRestWithPath[A](body)

  protected class DoRestWithPath[A](body: (WebResource#Builder) => A) {
    def path(path: String = "")(implicit basePath: String): A = {

      body(resource.path(basePath + path).accept(MT).`type`(MT)).asInstanceOf[A]
    }

    def pathWithQueryParam(query:(String, String), path: String = "")(implicit basePath: String): A = {
      val (key,value) = query
      body(resource.queryParam(key,value).path(basePath + path).accept(MT).`type`(MT)).asInstanceOf[A]
    }
  }
}