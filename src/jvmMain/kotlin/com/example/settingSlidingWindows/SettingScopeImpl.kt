package com.example.settingSlidingWindows

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.settingSlidingWindows.utils.SettingTypo
import com.example.settingSlidingWindows.utils.getIcon

internal class SettingScopeImpl(
    private val map: MutableMap<Int, (@Composable () -> Unit)?>,
    private val list: MutableList<@Composable () -> Unit>,
    private val settingState: SettingState,
    private val _settingColors: SettingColors,
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
                    color = _settingColors.container
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 25.dp, top = 40.dp, bottom = 50.dp),
                text = title,
                style = SettingTypo.titleLarge,
                color = _settingColors.onContainer
            )
        }
    }


    override fun item(
        settingColors: SettingColors?,
        icon: @Composable (() -> Unit)?,
        title: String,
        subTitle: String?,
        showTopLine: Boolean,
        advanceItemContent: (@Composable AdvanceSettingScope.() -> Unit)?,
    ) {
        val index = size
        list.add {
            baseItem(
                settingColors = settingColors ?: _settingColors,
                icon = icon,
                title = title,
                subTitle = subTitle,
                index = index,
                showTopLine = showTopLine
            )
        }
        if (advanceItemContent != null) {
            val advanceSettingScopeImpl = AdvanceSettingScopeImpl(
                settingState = settingState,
                _settingColors = _settingColors,
                _title = title
            )

            map[index] = {
                Column {
                    advanceSettingScopeImpl.advanceItemContent()
                }
            }
        }
        else {
            map[index] = null
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
                _settingColors = _settingColors
            )
            map[index] = {
                advanceSettingScopeImpl.advanceItemContent()
            }
        }
        else {
            map[index] = null
        }
        size++
    }


    @Composable
    private fun baseItem(
        settingColors: SettingColors,
        icon: @Composable (() -> Unit)?,
        title: String,
        subTitle: String?,
        index: Int,
        showTopLine: Boolean,
    ) {
        if (showTopLine) {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = settingColors.onContainer,
                thickness = 2.dp
            )
        }

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Row(
                modifier = Modifier
                    .background(
                        color = settingColors.container
                    )
                    .clickable(
                        onClick = {
                            settingState.openAdvance(index)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

                    IconButton(
                        onClick = {
                            settingState.openAdvance(index)
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
            color = _settingColors.onContainer,
            modifier = Modifier.padding(top = 25.dp, bottom = 5.dp)
        )
    }

}