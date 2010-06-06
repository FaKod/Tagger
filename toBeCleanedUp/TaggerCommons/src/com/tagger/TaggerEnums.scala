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
package com.tagger

/**
 * User: FaKod
 * Date: 31.12.2009
 * Time: 19:18:00
 */

/**
 * Type of Location used for Status object
 */
object LocationType extends Enumeration {
  val NextLocation, MyLocation, LocationTag = Value
}

/**
 * Type of Demand in a Status object (e.g. multiple choice question)
 */
object DemandType extends Enumeration {
  val Multiple, Single = Value
}

/**
 * Type of the Status Object itself
 */
object StatusType extends Enumeration {
  val CrossedLine, EnteredArea, LeftArea, Status, FreeText = Value
}

/**
 * Type of Observation used for a Plan
 */
object ObservationType extends Enumeration {
  val Nothing, SendEMAL = Value
}

/**
 * Type of Features
 */
object FeatureType extends Enumeration {
  val Line, Area = Value
}

/**
 * Type of Group/CircleOfFriendsType
 */
object CircleOfFriendsType extends Enumeration {
  val Open, Democratic, Dictatorial = Value
}
