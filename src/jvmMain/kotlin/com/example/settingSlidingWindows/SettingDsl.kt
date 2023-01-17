package com.example.settingSlidingWindows

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

interface SettingScope {


    fun item(
        icon: @Composable (() -> Unit)?,
        title: MutableState<String>,
        subTitle: MutableState<String>?,
        advanceItemContent: (AdvanceSettingScope.() -> Unit)?,
    )

    fun item(
        content: @Composable () -> Unit,
        advanceItemContent: (AdvanceSettingScope.() -> Unit)?,
    )


    fun topSetting(
        title: String,
    )


    fun topSetting(
        content: @Composable () -> Unit,
    )
}