package com.example.settingSlidingWindows

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

interface AdvanceSettingScope {


    fun advanceItem(
        content: LazyListScope.() -> Unit,
    )


    fun advanceItemPerso(
        content: @Composable () -> Unit,
    )
}