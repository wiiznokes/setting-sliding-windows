package com.example.settingSlidingWindows.firstView

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.settingSlidingWindows.SettingColors
import com.example.settingSlidingWindows.SettingState
import com.example.settingSlidingWindows.advance.AdvanceSettingScope
import com.example.settingSlidingWindows.advance.AdvanceSettingScopeImpl
import com.example.settingSlidingWindows.theme.SettingTypo
import com.example.settingSlidingWindows.utils.getIcon

internal class SettingScopeImpl(
    private val map: MutableMap<Int, (@Composable () -> Unit)?>,
    private val list: MutableList<@Composable () -> Unit>,
    private val settingState: MutableState<SettingState>,
    private val settingColors: SettingColors,
) : SettingScope {

    private var size = 0

    override fun header(
        title: String,
    ) {
        list.add {
            baseHeader(
                title = title
            )
        }
    }


    override fun header(
        content: @Composable () -> Unit,
    ) {
        list.add(content)
    }

    @Composable
    private fun baseHeader(
        title: String,
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = settingColors.container
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 25.dp, top = 40.dp, bottom = 50.dp),
                text = title,
                style = SettingTypo.titleLarge,
                color = settingColors.onContainer
            )
        }

        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = settingColors.onContainer,
            thickness = 2.dp
        )
    }


    override fun item(
        icon: @Composable (() -> Unit)?,
        title: String,
        subTitle: String?,
        advanceItemContent: (@Composable AdvanceSettingScope.() -> Unit)?,
    ) {
        val index = size
        list.add {
            baseItem(
                icon = icon,
                title = title,
                subTitle = subTitle,
                index = index
            )
        }
        if (advanceItemContent != null) {

            val advanceSettingScopeImpl = AdvanceSettingScopeImpl(
                settingState = settingState,
                _settingColors = settingColors,
                _title = title
            )

            map[index] = {
                advanceSettingScopeImpl.advanceItemContent()
            }
        }

        size++
    }


    override fun item(
        content: @Composable (Int) -> Unit,
        advanceItemContent: (@Composable AdvanceSettingScope.() -> Unit)?,
    ) {
        val index = size
        list.add {
            content(index)
        }

        if (advanceItemContent != null) {
            val advanceSettingScopeImpl = AdvanceSettingScopeImpl(
                settingState = settingState,
                _settingColors = settingColors
            )
            map[index] = {
                advanceSettingScopeImpl.advanceItemContent()
            }
        }
        size++
    }


    @Composable
    private fun baseItem(
        icon: @Composable (() -> Unit)?,
        title: String,
        subTitle: String?,
        index: Int,
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Row(
                modifier = Modifier
                    .background(
                        color = settingColors.container
                    )
                    .clickable(
                        onClick = {
                            settingState.value = settingState.value.copy(
                                isFistView = false,
                                advanceIndex = index
                            )
                            println("on click -> advanceIndex = $index")
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

                    IconButton(
                        onClick = {
                            settingState.value = settingState.value.copy(
                                isFistView = false,
                                advanceIndex = index
                            )
                            println("slider icon click -> advanceIndex = $index")
                        }
                    ) {
                        Icon(
                            painter = getIcon("chevron/chevron_right40"),
                            contentDescription = null,
                            tint = settingColors.onContainer
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (icon != null) {
                            icon()
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                        size++

                        Column {
                            Text(
                                text = title,
                                style = SettingTypo.bodyLarge,
                                overflow = TextOverflow.Clip,
                                maxLines = 1,
                                color = settingColors.onContainer
                            )
                            if (subTitle != null) {
                                Text(
                                    text = subTitle,
                                    style = SettingTypo.bodyMedium,
                                    overflow = TextOverflow.Clip,
                                    maxLines = 2,
                                    color = settingColors.onContainer
                                )
                            }
                        }
                    }
                }
            }
        }

        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp,
            color = settingColors.onContainer
        )
    }


    override fun group(text: String) {
        list.add {
            baseGroup(
                text = text
            )
        }
    }

    override fun group(content: @Composable () -> Unit) {
        list.add(content)
    }

    @Composable
    private fun baseGroup(
        text: String,
    ) {
        Text(
            text = text,
            style = SettingTypo.bodyLarge,
            color = settingColors.onContainer,
            modifier = Modifier.padding(top = 25.dp, bottom = 5.dp)
        )
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = settingColors.onContainer,
            thickness = 2.dp
        )
    }

}