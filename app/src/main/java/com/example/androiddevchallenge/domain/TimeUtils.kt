/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.domain

data class TimeData(val millis: Int, val seconds: Int, val minutes: Int, val hours: Int)

fun stringToMillis(label: String): Int {
    val rev = label.padStart(5, '0').reversed()
    val seconds = rev.substring(0, 2).reversed().toIntOrNull() ?: 0
    val minutes = rev.substring(2, 4).reversed().toIntOrNull() ?: 0
    val hours = rev.substring(4, rev.length).reversed().toIntOrNull() ?: 0
    return (hours * 3600) + (minutes * 60) + seconds
}

fun timestampToUnits(timestamp: Int): TimeData {
    val ms = timestamp % 1000
    val s = timestamp / 1000 % 60
    val m = timestamp / 1000 / 60 % 60
    val h = timestamp / 1000 / 60 / 60
    return TimeData(millis = ms, seconds = s, minutes = m, hours = h)
}
