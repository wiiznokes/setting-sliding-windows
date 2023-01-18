package com.example.settingSlidingWindows

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.example.settingSlidingWindows.utils.SettingTypo

interface SettingScope {

    /**
     * Premake header for the first view windows
     * @param title Display title
     */
    fun header(
        title: String,
    )

    /**
     * Header of the first view windows
     */
    fun header(
        content: @Composable () -> Unit,
    )


    /**
     * Premake item, witch handle slide interaction
     * @param icon Icon displayed of the left
     * @param title Title displayed of the middle top
     * @param subTitle Subtitle displayed of the bottom
     * @param showTopLine Should show top line
     * @param advanceItemContent Content when we open this setting
     */
    fun item(
        settingColors: SettingColors? = null,
        icon: @Composable (() -> Unit)? = null,
        title: String? = null,
        titleStyle: TextStyle = SettingTypo.bodyLarge,
        subTitle: String? = null,
        advanceIconButton: @Composable (() -> Unit)? = null,
        showTopLine: Boolean = false,
        advanceItemContent: (@Composable AdvanceSettingScope.() -> Unit)? = null,
    )

    /**
     * Customizable item. You have to change the state of [SettingState]
     * if you want to open your advanceItemContent
     * @param content Content of this item, Integer parameter represent
     * the index of this setting, used for custom item with [SettingState]
     * @param advanceItemContent Content when we open this setting
     */
    fun item(
        content: @Composable (Int) -> Unit,
        advanceItemContent: (@Composable AdvanceSettingScope.() -> Unit)? = null,
    )

    /**
     * Premake separator of setting, there will be a space
     * and a subtitle
     * @param text Text witch will be displayed
     */
    fun group(
        text: String,
        textStyle: TextStyle = SettingTypo.labelLarge
    )

    /**
     * Customisable group of setting
     */
    fun group(
        content: @Composable () -> Unit,
    )

}