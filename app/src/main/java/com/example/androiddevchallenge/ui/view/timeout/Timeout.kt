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
package com.example.androiddevchallenge.ui.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.view.timeout.TimeoutControlState
import com.example.androiddevchallenge.ui.view.timeout.TimeoutControls
import kotlin.math.ceil

@Composable
fun Timeout(modifier: Modifier = Modifier, fromMillis: Int = 0) {
    val (resumeTime, setResumeTime) = remember { mutableStateOf(fromMillis.toFloat()) }
    val (state, setState) = remember {
        mutableStateOf<TimeoutControlState>(TimeoutControlState.Paused)
    }
    val time = remember { Animatable(resumeTime) }

    LaunchedEffect(fromMillis) {
        setState(TimeoutControlState.Paused)
        setResumeTime(fromMillis.toFloat())
        time.snapTo(fromMillis.toFloat())
    }
    LaunchedEffect(state) {
        when (state) {
            is TimeoutControlState.Started -> {
                time.animateTo(
                    targetValue = 0.0f,
                    animationSpec = tween(
                        durationMillis = resumeTime.toInt(),
                        easing = LinearEasing
                    )
                )
            }
            is TimeoutControlState.Paused -> {
                val pauseTime = time.value.takeIf { it > 0f } ?: fromMillis.toFloat()
                setResumeTime(pauseTime)
                time.snapTo(pauseTime)
            }
        }
    }

    Box(
        modifier = modifier
            .padding(16.dp)
    ) {
        TimeoutProgressIndicator(
            startTimeMillis = fromMillis,
            timeMillis = time.value.toInt(),
            modifier = Modifier
                .align(Alignment.Center)
                .aspectRatio(1.0f)
        ) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                TimeoutText(
                    timestamp = ceil(time.value).toInt(),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                TimeoutControls(
                    state = state,
                    setState = setState,
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Preview
@Composable
fun TimeoutWith() {
    MyTheme {
        Timeout(modifier = Modifier.fillMaxSize(), fromMillis = 30000)
    }
}
