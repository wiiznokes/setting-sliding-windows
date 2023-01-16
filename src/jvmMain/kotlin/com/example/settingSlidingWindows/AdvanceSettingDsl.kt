package com.example.settingSlidingWindows

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

interface AdvanceSettingScope {


    @Composable
    fun advanceItem(
        sliderIcon: @Composable ()-> Unit,
        content: LazyListScope.()->Unit
    )

    @Composable
    fun advanceItem(
        content: @Composable ()->Unit
    )
}