package com.example.settingSlidingWindows.advance

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

interface AdvanceSettingScope {


    fun advanceItem(
        content: LazyListScope.() -> Unit,
    )


    fun advanceItemCustom(
        content: @Composable () -> Unit,
    )
}