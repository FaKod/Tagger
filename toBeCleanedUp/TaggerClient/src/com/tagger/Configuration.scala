/*
 * Copyright (c) 2009 to the resp. authors. All rights reserved.
 *
 * This document is the property of LinesOfCode,
 * it may not be copied or circulated without prior permission.
 */

package com.tagger

import org.apache.log4j.BasicConfigurator
import xml.XML
import javax.servlet.ServletContext
import java.io.File

/**
 * Created by IntelliJ IDEA.
 * User: FaKod
 * Date: 25.01.2010
 * Time: 14:53:09
 */

object Configuration {
  BasicConfigurator.configure()

  /**
   * path if no assignment to configurationPath_
   */
  private var configPath: String = "."
  private var BASE_URI: String = null

  /**
   * init method called on first access
   */
  private def init() = {
    val settings = XML.loadFile(configPath + File.separatorChar + "settings.xml")
    val entry = (settings \\ "tagger") \\ "server"
    val port = entry \\ "@port"
    val host = entry \\ "@host"
    val path = entry \\ "@path"
    BASE_URI = "http://" + host + ":" + port + "/" + path
  }

  /**
   * set configuration path as an absolute path
   * usage: <code>Configuration.configurationPath = "ThisIsMyPath"</code>
   */
  def configurationPath=configPath
  def configurationPath_=(p: String) = {
    configPath = p
  }

  /**
   * set the configuration path from servlet context
   * usage: <code>Configuration.configurationPath = myServletContextInstance</code>
   */
  def configurationPath_=(srvc: ServletContext) = {
    configPath = srvc.getRealPath("/")
  }

  /**
   * service base uri pointing to service base location
   */
  def getBaseURI = {
    if (BASE_URI == null) init
    BASE_URI
  }

  /**
   * timeout for client service
   */
  private final val CONNECT_TIMEOUT: Int = 120000

  def getConnectTimeout = CONNECT_TIMEOUT

  /**
   * timeout for client service
   */
  private final val READ_TIMEOUT: Int = 1200000

  def getReadTimeout = READ_TIMEOUT
} 