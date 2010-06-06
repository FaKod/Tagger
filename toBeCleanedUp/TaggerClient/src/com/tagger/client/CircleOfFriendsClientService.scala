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
import com.tagger.transfer.generated.{Tcircleoffriends, ECircleOfFriends, EIds}

/**
 * User: FaKod
 * Date: 12.02.2010
 * Time: 23:15:06
 */

trait CircleOfFriendsClientService {
  self: TaggerClientService =>

  private final val USER_RES_PATH = "circleoffriends/"
  private final implicit val basePath = USER_RES_PATH + "Cof/"

  /**
   * adding CircleOfFriends
   */
  def addCircleOfFriends(tcof: Tcircleoffriends): Tcircleoffriends = {
    val ecof:ECircleOfFriends = tcof
    val createdId =
    rest {
      client =>
        val cr = client.post(classOf[ClientResponse], ecof)

        if (cr.getStatus != ClientResponse.Status.CREATED.getStatusCode)
          throw new InternalServerException("addCircleOfFriends has not created a new CircleOfFriends. Status: " + cr.getStatus)

        getID(cr.getLocation.getPath)
    } path ()

    rest {
      _.get(classOf[ECircleOfFriends])
    } path (createdId)
  }

  /**
   * returning ECircleOfFriends
   */
  def getCof(cofid: String): Tcircleoffriends = {
    rest {
      _.get(classOf[ECircleOfFriends])
    } path (cofid)
  }

  /**
   * get all the users Cofs
   */
  def getCofIdsForUser(uid: String): java.util.List[String] = {
    implicit val basePath = USER_RES_PATH + "CofFromUser/"
    val ids =
    rest {
      _.get(classOf[EIds])
    } path (uid)
    ids.getIdArray.getIds
  }

  /**
   * delete a CircleOfFriends
   */
  def deleteCof(cofid: String): Unit = {
    rest {
      _.delete()
    } path (cofid)
  }

  /**
   * add a user to a Cof
   */
  def addUserIdToCof(cofid: String, uid:String): Tcircleoffriends = {
    val cof:Tcircleoffriends = getCof(cofid)
    val ecof:ECircleOfFriends = cof
    cof.getUsers.getIds.add(uid)
    rest {
      _.put(classOf[ECircleOfFriends], ecof)
    } path (cofid)
  }

  /**
   * remove user from Cof
   */
  def deleteUserIdFromCof(cofid: String, uid:String): Tcircleoffriends = {
    val cof:Tcircleoffriends = getCof(cofid)
    val ecof:ECircleOfFriends = cof
    cof.getUsers.getIds.remove(uid)
    rest {
      _.put(classOf[ECircleOfFriends], ecof)
    } path (cofid)
  }

  /**
   * update Cof
   */
  def updateCof(tcof: Tcircleoffriends): Tcircleoffriends = {
    val ecof:ECircleOfFriends = tcof
    rest {
      _.put(classOf[ECircleOfFriends], ecof)
    } path (tcof.getId)
  }
}