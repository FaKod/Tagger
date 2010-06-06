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

import com.tagger.security.LoginUser
import util.{Conversions, ClientServiceBase}

/**
 * User: FaKod
 * Date: 24.01.2010
 * Time: 20:06:51
 */

class TaggerClientService(override val user: LoginUser) extends ClientServiceBase(user) with Conversions
        with UserClientService
        with LocationTagClientService
        with StatusClientService
        with CircleOfFriendsClientService {
  /**
   * no special sub-path
   */
  final val servicePath = ""
}