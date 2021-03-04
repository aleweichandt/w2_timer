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
package com.example.androiddevchallenge.ui.view.timeout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import java.io.Serializable

sealed class TimeoutControlState : Serializable {
    object Started : TimeoutControlState()
    object Paused : TimeoutControlState()
}

@Composable
fun TimeoutControls(
    state: TimeoutControlState,
    setState: (TimeoutControlState) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        when (state) {
            is TimeoutControlState.Paused -> {
                IconButton(
                    onClick = { setState(TimeoutControlState.Started) },
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play),
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = stringResource(
                            id = R.string.play
                        )
                    )
                }
            }
            is TimeoutControlState.Started -> {
                IconButton(
                    onClick = { setState(TimeoutControlState.Paused) },
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pause),
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = stringResource(
                            id = R.string.pause
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TimeoutControlPaused() {
    TimeoutControls(state = TimeoutControlState.Paused, setState = { })
}

@Preview
@Composable
fun TimeoutControlStarted() {
    TimeoutControls(state = TimeoutControlState.Started, setState = { })
}
