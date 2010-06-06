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
 * Time: 20:25:30
 */

/**UserClientService
 * trait for module user resource
 */
trait  UserClientService {
  self: TaggerClientService =>

  private final val USER_RES_PATH = "user/"
  private final implicit val basePath = USER_RES_PATH + "User/"

  /**
   * adding user and user info
   */
  def addUserAndInfo(u: Tuser, ui: Tuserinfo): Tuser = {
    val eu = new EUserAndUserInfo
    eu.setUser(u)
    eu.setUserInfo(ui)
    addUserAndInfo(eu)
  }

  /**
   * adding user and user info
   */
  private def addUserAndInfo(eu: EUserAndUserInfo): Tuser = {
    val createdId =
    rest {
      client =>
        val cr = client.post(classOf[ClientResponse], eu)

        if (cr.getStatus != ClientResponse.Status.CREATED.getStatusCode)
          throw new InternalServerException("addUserAndInfo has not created a new User and Info. Status: " + cr.getStatus)

        getID(cr.getLocation.getPath)
    } path ()

    rest {
      _.get(classOf[EUser])
    } path (createdId)
  }

  /**
   * get user with id
   */
  def getUser(userId: String): Tuser = {
    rest {
      _.get(classOf[EUser])
    } path (userId)
  }

  /**
   * get user with user principal
   */
  def getLoggedInUser(): Tuser = {
    rest {
      _.get(classOf[EUser])
    } path ()
  }

  /**
   * get user and info with id
   */
  def getUserInfo(userId: String): Tuserinfo = {
    implicit val basePath = USER_RES_PATH + "UserInfo/"

    rest {
      _.get(classOf[EUserInfo])
    } path (userId)
  }

  /**
   * find user with name
   */
  def findUser(name: String): TidNameArray = {
    implicit val basePath = USER_RES_PATH + "FindUser/"

    rest {
      _.get(classOf[EIdNames])
    } pathWithQueryParam (("name",name))
  }

  /**
   * update UserInfo for user id
   */
  def updateUserInfo(tui: Tuserinfo, userId: String): Tuserinfo = {
    implicit val basePath = USER_RES_PATH + "UserInfo/"

    val ui:EUserInfo = tui

    rest {
      _.put(classOf[EUserInfo], ui)
    } path (userId)
  }

  /**
   * deletes a user
   */
  def deleteUser(userId: String): Unit = {
    rest {
      _.delete()
    } path (userId)
  }
}