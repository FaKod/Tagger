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

import com.tagger.util.ScalaThreadLocal
import com.tagger.security.LoginUser

/**.
 * User: FaKod
 * Date: 26.01.2010
 * Time: 06:41:08
 */

object TaggerClientServiceFactory {
  private val s = new ScalaThreadLocal[TaggerClientService]()

  /**
   * creates a new TaggerClientService instance or returnes the associated
   */
  def apply(user: LoginUser) = s get match {
    case Some(x) => x
    case None => s set Option(new TaggerClientService(user)); s.get.get
  }

  /**
   * releases the TaggerClientService instance
   */
  def release() = s.set(None)
}
