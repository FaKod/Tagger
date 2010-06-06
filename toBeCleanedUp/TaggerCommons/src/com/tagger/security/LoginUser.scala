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
package com.tagger.security

import com.sun.org.apache.xml.internal.security.utils.Base64

/**
 * Created by IntelliJ IDEA.
 * User: christopherschmidt
 * Date: 15.02.2010
 * Time: 09:53:14
 * To change this template use File | Settings | File Templates.
 */

/**
 * Users Logon object
 */
class LoginUser(user: String, role: String, pw: String = null) {
  def username = user

  def userrole = role

  def password = pw

  /**
   * returns the User's Credentials which are Base64 encoded.
   * @return the Base64 encoded Credentials as a String.
   */
  def userCredentials(): String = {
    "Basic " + new String(Base64.encode((username + ":" + password).getBytes));
  }
}