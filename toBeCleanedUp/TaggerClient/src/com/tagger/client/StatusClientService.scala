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

import com.tagger.transfer.generated.{EStatusReturn, EStatus}

/**
 * User: FaKod
 * Date: 02.02.2010
 * Time: 20:34:07
 */

trait StatusClientService {
  self: TaggerClientService =>

  private final val LOCATIONTAG_RES_PATH = "Status/"
  private final implicit val basePath = LOCATIONTAG_RES_PATH

  def setStatus(es:EStatus):EStatusReturn = {
    rest {
      _.put(classOf[EStatusReturn], es)
    } path ()
  }
}