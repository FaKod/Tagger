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

import com.tagger.transfer.generated._

/**
 * User: FaKod
 * Date: 12.02.2010
 * Time: 23:15:06
 */

trait Conversions {

  /**
   * for ECircleOfFriends
   */
  implicit def TCof_ECof(t:Tcircleoffriends):ECircleOfFriends = {
    val e = new ECircleOfFriends
    e.setCircleOfFriends(t)
    e
  }

  implicit def ECof_TCof(e:ECircleOfFriends):Tcircleoffriends = e.getCircleOfFriends

  /**
   * for User
   */
  implicit def TUser_EUser(t:Tuser):EUser = {
    val e = new EUser
    e.setUser(t)
    e
  }

  implicit def EUser_TUser(e:EUser):Tuser = e.getUser

  implicit def EIdNames_TidNameArray(e:EIdNames):TidNameArray = e.getIdNameArray

  /**
   * for UserInfo
   */
  implicit def TUserInfo_EUserInfo(t:Tuserinfo):EUserInfo = {
    val e = new EUserInfo
    e.setUserInfo(t)
    e
  }

  implicit def EUserInfo_TUserInfo(e:EUserInfo):Tuserinfo = e.getUserInfo

  /**
   * for LocationTag
   */
  implicit def TLocationTag_ELocationTag(t:Tlocationtag):ELocationTag = {
    val e = new ELocationTag
    e.setLocationTag(t)
    e
  }

  implicit def ELocationTag_TLocationTag(e:ELocationTag):Tlocationtag = e.getLocationTag
}