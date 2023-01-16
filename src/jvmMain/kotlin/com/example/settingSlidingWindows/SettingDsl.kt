package com.example.settingSlidingWindows

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

interface SettingScope {

    @Composable
    fun item(
        icon: @Composable (() -> Unit)?,
        title: MutableState<String>,
        subTitle: MutableState<String>?,
        sliderIcon: @Composable ()-> Unit,
        advanceItemContent:@Composable AdvanceSettingScope.() -> Unit
    )
    @Composable
    fun item(
        content: @Composable ()->Unit
    )


    @Composable
    fun topSetting(
        title: String
    )

    @Composable
    fun topSetting(
        content: @Composable ()-> Unit
    )
}