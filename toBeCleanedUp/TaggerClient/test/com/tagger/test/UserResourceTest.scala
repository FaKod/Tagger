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

import com.tagger.security.LoginUser
import com.tagger.client.TaggerClientServiceFactory
import org.specs.Specification
import com.tagger.transfer.generated.{Tlocation, Tuserinfo, Tuser}
import com.tagger.LocationType
import collection.mutable.Queue
import com.sun.jersey.api.client.UniformInterfaceException

/**
 * User: FaKod
 * Date: 01.01.2010
 * Time: 12:07:38
 */

object UserResourceTest extends Specification {
  "A User service" should {

    setSequential()

    shareVariables()
    var s = TaggerClientServiceFactory(new LoginUser("FaKod", "Role", "password"))
    var ids = new Queue[String]()

    "add 10 User" in {

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
        ui.setFullname("Fakod Fakod" + i)
        val createdUser = s.addUserAndInfo(u, ui)
        ids.enqueue(createdUser.getId)
      }
      ids.size must_== 10
    }

    "read 10 User entities" in {
      ids.foreach {
        uId =>
          val user = s.getUser(uId)
          user.getId must_== uId
      }
    }

    "read 10 UserInfo entities" in {
      ids.foreach {
        uId =>
          val userInfo = s.getUserInfo(uId)
          userInfo.getEmail must_== "info@fakod.eu"
      }
    }

    "update 10 UserInfo entities" in {
      ids.foreach {
        uId =>
          var userInfo = s.getUserInfo(uId)
          userInfo.setFullname("Lines Of Code")
          val userInfoReturn = s.updateUserInfo(userInfo, uId)

          val userInfoGet = s.getUserInfo(uId)

          userInfoReturn.getId must_== userInfoGet.getId
          userInfoReturn.getEmail must_== userInfoGet.getEmail
          userInfoReturn.getFullname must_== userInfoGet.getFullname
          userInfoReturn.getFullname must_== "Lines Of Code"
      }
    }

    "find User entities" in {
      s.findUser("") must throwAn[UniformInterfaceException]
      s.findUser("1") must throwAn[UniformInterfaceException]

      val users = s.findUser("10")
      users.getIds.size must beGreaterThan(0)
    }

    "delete 10 User entities" in {
      ids.foreach {
        uId =>
          s.deleteUser(uId)
          val user = s.getUser(uId) must throwAn[UniformInterfaceException]
      }
    }
  }
}