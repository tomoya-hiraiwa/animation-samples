/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.activityscenetransitionbasic

/**
 * Represents an Item in our application. Each item has a name, id, full size image url and
 * thumbnail url.
 */
class Item internal constructor(
    val name: String,
    val author: String,
    private val mFileName: String
) {
    val id: Int
        get() = name.hashCode() + mFileName.hashCode()
    val photoUrl: String
        get() = LARGE_BASE_URL + mFileName
    val thumbnailUrl: String
        get() = THUMB_BASE_URL + mFileName

    companion object {
        private const val LARGE_BASE_URL =
            "https://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/large/"
        private const val THUMB_BASE_URL =
            "https://storage.googleapis.com/androiddevelopers/sample_data/activity_transition/thumbs/"
        @JvmField
        var ITEMS = arrayOf(
            Item("Flying in the Light", "Romain Guy", "flying_in_the_light.jpg"),
            Item("Caterpillar", "Romain Guy", "caterpillar.jpg"),
            Item("Look Me in the Eye", "Romain Guy", "look_me_in_the_eye.jpg"),
            Item("Flamingo", "Romain Guy", "flamingo.jpg"),
            Item("Rainbow", "Romain Guy", "rainbow.jpg"),
            Item("Over there", "Romain Guy", "over_there.jpg"),
            Item("Jelly Fish 2", "Romain Guy", "jelly_fish_2.jpg"),
            Item("Lone Pine Sunset", "Romain Guy", "lone_pine_sunset.jpg")
        )

        fun getItem(id: Int): Item? {
            for (item in ITEMS) {
                if (item.id == id) {
                    return item
                }
            }
            return null
        }
    }
}