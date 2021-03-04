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
package com.example.androiddevchallenge.ui.view.keyboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Keyboard(value: String, setValue: (String) -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Row(modifier = Modifier.matchParentSize()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentSize(Alignment.TopCenter)
            ) {
                Key(value = "1", onClick = { setValue("${value}1") })
                Key(value = "4", onClick = { setValue("${value}4") })
                Key(value = "7", onClick = { setValue("${value}7") })
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentSize(Alignment.TopCenter)
            ) {
                Key(value = "2", onClick = { setValue("${value}2") })
                Key(value = "5", onClick = { setValue("${value}5") })
                Key(value = "8", onClick = { setValue("${value}8") })
                Key(value = "0", onClick = { setValue("${value}0") })
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentSize(Alignment.TopCenter)
            ) {
                Key(value = "3", onClick = { setValue("${value}3") })
                Key(value = "6", onClick = { setValue("${value}6") })
                Key(value = "9", onClick = { setValue("${value}9") })
                Key(
                    value = "<",
                    onClick = { setValue(value.substring(0, maxOf(value.length - 1, 0))) }
                )
            }
        }
    }
}

@Composable
fun Key(value: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = value,
            textAlign = TextAlign.Center,
            modifier = Modifier.wrapContentSize(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun KeyboardWithWord() {
    val (word, setWord) = remember { mutableStateOf("") }
    Keyboard(word, setWord, Modifier.fillMaxSize())
}
