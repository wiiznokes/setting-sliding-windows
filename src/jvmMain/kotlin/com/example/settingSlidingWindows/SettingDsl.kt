package com.example.settingSlidingWindows

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

interface SettingScope {


    fun item(
        icon: @Composable (() -> Unit)? = null,
        title: MutableState<String>,
        subTitle: MutableState<String>? = null,
        advanceItemContent: (AdvanceSettingScope.() -> Unit)? = null,
    )

    fun item(
        content: @Composable (Int) -> Unit,
        advanceItemContent: (AdvanceSettingScope.() -> Unit)? = null,
    )


    fun topSetting(
        title: String,
    )


    fun topSetting(
        content: @Composable () -> Unit,
    )
}