package com.example.settingSlidingWindows

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

interface AdvanceSettingScope {


    fun advanceItem(
        sliderIcon: @Composable () -> Unit,
        content: LazyListScope.() -> Unit,
    )


    fun advanceItem(
        content: @Composable () -> Unit,
    )
}