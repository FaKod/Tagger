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
package com.tagger.exception

/**
 * Created by IntelliJ IDEA.
 * User: christopher schmidt
 * Date: 25.01.2010
 * Time: 12:55:16
 * To change this template use File | Settings | File Templates.
 */

/**
 * exception thrown if inernal server failure failure
 */
class InternalServerException(message: String, cause: Throwable = null) extends TaggerException(message, cause) {
}
