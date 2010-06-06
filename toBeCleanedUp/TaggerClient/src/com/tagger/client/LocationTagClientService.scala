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
package com.tagger.client

import com.sun.jersey.api.client.ClientResponse
import com.tagger.exception.InternalServerException
import com.tagger.transfer.generated._

/**
 * User: FaKod
 * Date: 02.02.2010
 * Time: 20:26:54
 */

/**
 * trait for module LocationTagResource
 */
trait LocationTagClientService {
  self: TaggerClientService =>

  private final val LOCATIONTAG_RES_PATH = "locationtag/"
  private final implicit val basePath = LOCATIONTAG_RES_PATH + "LocationTag/"

  /**
   * adding a location tag
   */
  def addLocationTag(uid: String, telt: Tlocationtag):Tlocationtag = {
    val elt:ELocationTag = telt
    val createdId =
    rest {
      client =>
        val cr = client.post(classOf[ClientResponse], elt)

        if (cr.getStatus != ClientResponse.Status.CREATED.getStatusCode)
          throw new InternalServerException("addLocationTag has not created a new LocationTag. Status: " + cr.getStatus)

        getID(cr.getLocation.getPath)
    } path (uid)

    rest {
      _.get(classOf[ELocationTag])
    } path (createdId)
  }

  /**
   * gets a location tag
   */
  def getLocationTag(lTid: String):Tlocationtag = {
    rest {
      _.get(classOf[ELocationTag])
    } path (lTid)
  }

  /**
   * get all loaction tag id owned by User uid
   */
  def getLocationTagsFromUser(uid: String): java.util.List[String] = {
    implicit val basePath = LOCATIONTAG_RES_PATH + "LocationTags/User/"
    val ids =
    rest {
      _.get(classOf[EIds])
    } path (uid)
    ids.getIdArray.getIds
  }

  /**
   * get all loaction tag id owned by Cof id
   */
  def getLocationTagsFromCof(cofid: String): java.util.List[String] = {
    implicit val basePath = LOCATIONTAG_RES_PATH + "LocationTags/Cof/"
    val ids =
    rest {
      _.get(classOf[EIds])
    } path (cofid)
    ids.getIdArray.getIds
  }

  /**
   * delete location tag
   */
  def deleteLocationTag(lTid: String): Unit = {
    rest {
      _.delete()
    } path (lTid)
  }

  /**
   * update location tag
   */
  def updateLocationTag(lTid: String, telt: Tlocationtag):Tlocationtag = {
    val elt:ELocationTag = telt
    rest {
      _.put(classOf[ELocationTag], elt)
    } path (lTid)
  }
}
