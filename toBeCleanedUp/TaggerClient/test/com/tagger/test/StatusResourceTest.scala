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

import com.tagger.LocationType
import org.specs.Specification
import com.tagger.client.TaggerClientServiceFactory
import collection.mutable.Queue
import com.tagger.transfer.generated.{EStatus, Tlocation, Tuser, Tuserinfo}
import util.Random
import com.tagger.security.LoginUser

/**
 * User: FaKod
 * Date: 02.02.2010
 * Time: 20:21:35
 */

object StatusResourceTest extends Specification {
  "a Status service" should {
    setSequential()
    shareVariables()
    var s = TaggerClientServiceFactory(new LoginUser("FaKod", "Role", "password"))
    var ids = new Queue[String]()
    var rnd = new Random().nextDouble

    doFirst {
      for (i <- 1 to 10) {
        val l = new Tlocation
        l.setLatitude(1.0)
        l.setLongitude(2.0)
        l.setLocationType(LocationType.MyLocation.toString)

        val u = new Tuser
        u.setName("FaKod" + i + rnd)
        u.setDirection(3.0)
        u.setLocation(l)

        val ui = new Tuserinfo
        ui.setEmail("info@fakod.eu")
        ui.setFullname("Fakod Fakod")
        val createdUser = s.addUserAndInfo(u, ui)
        ids.enqueue(createdUser.getId)
      }
    }

    "update location" in {
      var count: Int = 1
      ids.foreach {
        uId =>
          TaggerClientServiceFactory.release
          val s = TaggerClientServiceFactory(new LoginUser("FaKod" + count + rnd, "Role", "password"))

          val user = s.getLoggedInUser()
          user.getId must_== uId

          val es = new EStatus

          val l = new Tlocation
          l.setLatitude(9.9 * count)
          l.setLongitude(9.9 * count)
          count += 1
          l.setLocationType(LocationType.MyLocation.toString)
          es.setLocation(l)

          val retStatus = s.setStatus(es)

          println("Distance: " + retStatus.getDistance)
          println("Azimuth: " + retStatus.getAzimuth)

          (retStatus.getDistance) must beGreaterThan(0.0)

          val userNew = s.getLoggedInUser()

          user.getLocation.getLatitude must_!= userNew.getLocation.getLatitude
          user.getLocation.getLongitude must_!= userNew.getLocation.getLongitude
      }
    }
  }
}