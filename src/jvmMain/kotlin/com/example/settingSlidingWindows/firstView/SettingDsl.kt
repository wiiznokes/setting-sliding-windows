package com.example.settingSlidingWindows.firstView

import androidx.compose.runtime.Composable
import com.example.settingSlidingWindows.advance.AdvanceSettingScope

interface SettingScope {
    fun header(
        title: String,
    )


    fun header(
        content: @Composable () -> Unit,
    )

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

    fun group(
        text: String,
    )

    fun group(
        content: @Composable () -> Unit,
    )

}