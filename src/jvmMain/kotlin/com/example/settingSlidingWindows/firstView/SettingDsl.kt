package com.example.settingSlidingWindows.firstView

import androidx.compose.runtime.Composable
import com.example.settingSlidingWindows.advance.AdvanceSettingScope

interface SettingScope {


    fun item(
        icon: @Composable (() -> Unit)? = null,
        title: String,
        subTitle: String? = null,
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