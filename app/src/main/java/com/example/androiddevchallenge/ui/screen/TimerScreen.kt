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
package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.domain.stringToMillis
import com.example.androiddevchallenge.ui.view.Timeout
import com.example.androiddevchallenge.ui.view.keyboard.Keyboard

@Composable
fun TimerScreen() {
    Scaffold(
        topBar = { TimerScreenBar() }
    ) {
        TimerScreenBody()
    }
}

@Composable
fun TimerScreenBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        }
    )
}

@Preview
@Composable
fun TimerScreenBody() {
    Surface {
        Column {
            val (input, setInput) = remember { mutableStateOf("0") }
            val intTime = stringToMillis(input)

            Timeout(
                fromMillis = intTime * 1000,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Keyboard(
                value = input,
                setValue = setInput,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }
}
