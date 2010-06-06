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
import com.sun.jersey.api.client.UniformInterfaceException
import collection.mutable.Queue
import com.tagger.security.LoginUser
import com.tagger.client.TaggerClientServiceFactory
import com.tagger.transfer.generated._
import com.tagger.{CircleOfFriendsType, LocationType}

/**
 * Created by IntelliJ IDEA.
 * User: christopherschmidt
 * Date: 01.02.2010
 * Time: 08:25:23
 * To change this template use File | Settings | File Templates.
 */

object LocationTagResourceTest extends Specification {
  "a LocationTag service" should {
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


    "add location tags" in {
      var count:Int = 0
      ids.foreach {
        uId =>
          val tlt = new Tlocationtag

          tlt.setInfotext("This is my Location Tag")
          tlt.setName("Fakod")

          val loc = new Tlocation
          loc.setLatitude(1.1)
          loc.setLongitude(2.2)
          tlt.setPoint(loc)


          val locationTag = s.addLocationTag(uId, tlt)
          locationTag.getInfotext must_== "This is my Location Tag"

          count += 1
          for(i <- 1 to count) {
            s.addLocationTag(uId, tlt)
          }
      }
    }

    "get LocationTags for User" in {
      var count:Int = 1
      ids.foreach {
        uId =>
        
        val ltids = s.getLocationTagsFromUser(uId)
        count += 1
        ltids.size must_== count

        ltids.toArray.foreach {
          ltid =>
            val lt = s.getLocationTag(ltid.asInstanceOf[String])
            lt.getId must_== ltid
        }
      }
    }

    "get location tags" in {
      var count:Int = 2
      ids.foreach {
        uId =>
          val lt = s.getUser(uId).getLocationtags.getIds
          lt.size must_== count
          count += 1
      }
    }

    "get location tags for Cof" in {
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

      val tcofNew = s.addCircleOfFriends(tcof)

      val tags = s.getLocationTagsFromCof(tcofNew.getId)
      tags.size must_==65
    }

    "update location tag" in {
      ids.foreach {
        uId =>
          val lt = s.getUser(uId).getLocationtags.getIds
          lt.toArray.foreach {
            ido =>
              val id = ido.asInstanceOf[String]
              val lt = s.getLocationTag(id)
              lt.setName("updated name")
              val newLt = s.updateLocationTag(id, lt)
              newLt.getName must_== "updated name"
          }
      }
      ids.foreach {
        uId =>
          val lt = s.getUser(uId).getLocationtags.getIds
          lt.toArray.foreach {
            ido =>
              val id = ido.asInstanceOf[String]
              val lt = s.getLocationTag(id)
              lt.getName must_== "updated name"
          }
      }
    }

    "delete location tags" in {
      ids.foreach {
        uId =>
          val lt = s.getUser(uId).getLocationtags.getIds
          lt.toArray.foreach {
            ido =>
              val id = ido.asInstanceOf[String]
              s.deleteLocationTag(id)
          }
      }
      ids.foreach {
        uId =>
          val lt = s.getUser(uId).getLocationtags.getIds
          lt.size must_== 0
      }
    }
  }
}