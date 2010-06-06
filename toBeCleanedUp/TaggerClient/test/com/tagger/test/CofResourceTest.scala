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
package com.tagger.test

import org.specs.Specification
import com.tagger.client.TaggerClientServiceFactory
import com.tagger.security.LoginUser
import collection.mutable.Queue
import com.tagger.transfer.generated._
import com.tagger.{CircleOfFriendsType, LocationType}
import com.sun.jersey.api.client.UniformInterfaceException

/**
 * User: FaKod
 * Date: 14.02.2010
 * Time: 15:08:53
 */

object CofResourceTest extends Specification {
  "a Circle Of Friends service" should {
    setSequential()
    shareVariables()
    var s = TaggerClientServiceFactory(new LoginUser("FaKod", "Role", "password"))
    var ids = new Queue[String]()

    doFirst {
      for (i <- 1 to 10) {
        val l = new Tlocation
        l.setLatitude(1.0 + i)
        l.setLongitude(2.0 + i)
        l.setLocationType(LocationType.MyLocation.toString)

        val u = new Tuser
        u.setName(i.toString)
        u.setDirection(3.0)
        u.setLocation(l)

        val ui = new Tuserinfo
        ui.setEmail("info@fakod.eu")
        ui.setFullname("Fakod Fakod")
        val createdUser = s.addUserAndInfo(u, ui)
        ids.enqueue(createdUser.getId)
      }
    }

//    doLast {
//      ids.foreach {
//        uId =>
//          s.deleteUser(uId)
//      }
//    }


    "add and get Cof" in {
      val tcof = new Tcircleoffriends

      val ar = new TidArray
      tcof.setUsers(ar)
      ids.foreach {
        uId =>
        ar.getIds.add(uId)
      }
      tcof.setName("10 Users")
      tcof.setOwningUserId(ids.get(0).get)
      tcof.setCircleoffriendstype(CircleOfFriendsType.Democratic.toString)

      /**
       * test
       */
      val tcofNew = s.addCircleOfFriends(tcof)

      val returnedTCof = s.getCof(tcofNew.getId)

      returnedTCof.getUsers.getIds.size must_== 10
    }

    "get Cof for user" in {
      val cof = s.getCofIdsForUser(ids.get(0).get)

      cof.size must_== 1

      val returnedTCof = s.getCof(cof.get(0))

      returnedTCof.getUsers.getIds.size must_== 10
    }

    "delete Cof" in {
      val cof = s.getCofIdsForUser(ids.get(0).get)
      val returnedTCof = s.getCof(cof.get(0))

      s.deleteCof(returnedTCof.getId)

      s.getCof(returnedTCof.getId) must throwAn[UniformInterfaceException]
    }

    "add/remove single user and get Cof" in {
      val tcof = new Tcircleoffriends

      val ar = new TidArray
      tcof.setUsers(ar)

      ar.getIds.add(ids.get(1).get)
      ar.getIds.add(ids.get(2).get)

      tcof.setName("add single user and get Cof")
      tcof.setOwningUserId(ids.get(0).get)
      tcof.setCircleoffriendstype(CircleOfFriendsType.Democratic.toString)

      // adding Cof
      val tcofNew = s.addCircleOfFriends(tcof)

      // get Cof with same ID
      var returnedTCof = s.getCof(tcofNew.getId)
      // must have 2 users
      returnedTCof.getUsers.getIds.size must_== 2

      /**
       * adding 1 user
       */
      s.addUserIdToCof(tcofNew.getId, ids.get(3).get)

      returnedTCof = s.getCof(returnedTCof.getId)
      returnedTCof.getUsers.getIds.size must_== 3

      /**
       * adding same user
       */
      s.addUserIdToCof(tcofNew.getId, ids.get(3).get)

      returnedTCof = s.getCof(returnedTCof.getId)
      returnedTCof.getUsers.getIds.size must_== 3 // has still to be 3

      /**
       * adding another user
       */
      s.addUserIdToCof(tcofNew.getId, ids.get(4).get)

      returnedTCof = s.getCof(returnedTCof.getId)
      returnedTCof.getUsers.getIds.size must_== 4

      /**
       * removing user
       */
      s.deleteUserIdFromCof(tcofNew.getId, ids.get(3).get)

      returnedTCof = s.getCof(returnedTCof.getId)
      returnedTCof.getUsers.getIds.size must_== 3

      /**
       * adding and removing user
       */
      s.addUserIdToCof(tcofNew.getId, ids.get(5).get)
      s.deleteUserIdFromCof(tcofNew.getId, ids.get(2).get)

      returnedTCof = s.getCof(returnedTCof.getId)
      returnedTCof.getUsers.getIds.size must_== 3
    }

    "update Cof" in {
      val tcof = new Tcircleoffriends

      val ar = new TidArray
      tcof.setUsers(ar)

      ar.getIds.add(ids.get(1).get)
      ar.getIds.add(ids.get(2).get)

      tcof.setName("update Cof")
      tcof.setOwningUserId(ids.get(0).get)
      tcof.setCircleoffriendstype(CircleOfFriendsType.Democratic.toString)

      // adding Cof
      var tcofNew = s.addCircleOfFriends(tcof)

      tcofNew.setCircleoffriendstype(CircleOfFriendsType.Dictatorial.toString)
      tcofNew.setName("update Cof this is updated")
      tcofNew = s.updateCof(tcofNew)

      tcofNew.getName must_== "update Cof this is updated"
      tcofNew.getCircleoffriendstype must_== CircleOfFriendsType.Dictatorial.toString

      val returnedTCof = s.getCof(tcofNew.getId)
      returnedTCof.getName must_== "update Cof this is updated"
      returnedTCof.getCircleoffriendstype must_== CircleOfFriendsType.Dictatorial.toString
    }
  }
}