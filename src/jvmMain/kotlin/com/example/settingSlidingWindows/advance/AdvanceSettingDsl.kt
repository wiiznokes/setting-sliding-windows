package com.example.settingSlidingWindows.advance

import androidx.compose.runtime.Composable
import com.example.settingSlidingWindows.SettingColors


interface AdvanceSettingScope {

    @Composable
    fun header(
        title: String?,
        settingColors: SettingColors?,
    )
}