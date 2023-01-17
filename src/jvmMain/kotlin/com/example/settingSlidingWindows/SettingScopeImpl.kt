package com.example.settingSlidingWindows

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class SettingScopeImpl(
    private val map: MutableMap<Int, (@Composable () -> Unit)?>,
    private val list: MutableList<@Composable () -> Unit>,
    private val settingState: MutableState<SettingState>
) : SettingScope {

    private var size = 0

    override fun item(
        icon: @Composable (() -> Unit)?,
        title: MutableState<String>,
        subTitle: MutableState<String>?,
        advanceItemContent: (AdvanceSettingScope.() -> Unit)?,
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

        val advanceSettingScopeImpl = AdvanceSettingScopeImpl(
            map = map,
            settingState = settingState,
            index = index,
            title = title.value
        )
        if (advanceItemContent != null) {
            advanceSettingScopeImpl.advanceItemContent()
        }

        size++
    }


    override fun item(
        content: @Composable (Int) -> Unit,
        advanceItemContent: (AdvanceSettingScope.() -> Unit)?,
    ) {
        val index = size
        list.add {
            content(index)
        }

        if (advanceItemContent != null) {
            val advanceSettingScopeImpl = AdvanceSettingScopeImpl(
                map = map,
                settingState = settingState,
                index = index
            )
            advanceSettingScopeImpl.advanceItemContent()
        }
        size++
    }


    override fun topSetting(
        title: String,
    ) {
        list.add {
            baseTopSetting(
                title = title
            )
        }
    }


    override fun topSetting(
        content: @Composable () -> Unit,
    ) {
        list.add(content)
    }


    @Composable
    private fun baseItem(
        icon: @Composable (() -> Unit)?,
        title: MutableState<String>,
        subTitle: MutableState<String>?,
        index: Int,
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Row(
                modifier = Modifier
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
                            contentDescription = null
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
                                text = title.value,
                                style = MaterialTheme.typography.bodyLarge,
                                overflow = TextOverflow.Clip,
                                maxLines = 2
                            )
                            if (subTitle != null) {
                                Text(
                                    text = subTitle.value,
                                    overflow = TextOverflow.Clip,
                                    maxLines = 2
                                )
                            }
                        }
                    }
                }
            }
        }

        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp
        )
    }


    @Composable
    private fun baseTopSetting(
        title: String,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 25.dp, top = 40.dp, bottom = 50.dp),
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        }

        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.inverseOnSurface,
            thickness = 2.dp
        )
    }

}