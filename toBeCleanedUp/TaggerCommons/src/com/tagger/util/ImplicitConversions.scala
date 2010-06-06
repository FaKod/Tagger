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
package com.tagger.util

import com.tagger.transfer.generated.TidArray

/**
 * User: FaKod
 * Date: 16.02.2010
 * Time: 20:06:21
 */

/**
 * adds an forEachIdArray to TidArray
 */
class RichIdArray(idArr: TidArray) {

  /**
   * for each method to TidArray
   */
  def foreach(f: String => Unit): Unit = {
    if (idArr != null) {
      val ids = idArr.getIds
      if (ids != null) {
        val iter = ids.iterator
        while (iter.hasNext) {
          f(iter.next)
        }
      }
    }
  }
}

/**
 * for Collection
 */
class RichCollection[T](c: java.util.Collection[T]) {

  /**
   * for each element
   */
  def foreach(f: T => Unit): Unit = {
    val iter = c.iterator
    while (iter.hasNext) {
      f(iter.next)
    }
  }
}

/**
 *  set of implicit conversions (enrichment)
 */
object ImplicitConversions {

  implicit def IdArrayToRichIdArray(idArr: TidArray) = new RichIdArray(idArr)

  implicit def CollectionToRichCollection[T](c: java.util.Collection[T]) = new RichCollection[T](c)
}