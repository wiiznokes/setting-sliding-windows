package com.example.settingSlidingWindows

import androidx.compose.runtime.Composable


interface AdvanceSettingScope {


    /**
     * Header of the advance setting windows. Displays a back arrow and title.
     * title and settingColors params are nullable because they can be retrieved
     * from SettingScope, but it's impossible to make default parameter
     * in a composable function inside an interface.
     * @param title title of header
     * @param settingColors colors used in the header
     */
    @Composable
    fun Header(
        title: String?,
        settingColors: SettingColors?,
    )
}