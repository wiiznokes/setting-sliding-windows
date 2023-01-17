package com.example.settingSlidingWindows

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


data class SettingState(
    val isFistView: Boolean = true,
    val advanceIndex: Int = 0
)

@Composable
fun rememberSettingState(
    initialValue: SettingState = SettingState(),
    key: Any = Unit
): MutableState<SettingState> {
    return remember(key) {
        mutableStateOf(initialValue)
    }
}

@Composable
fun Setting(
    settingState: MutableState<SettingState> = rememberSettingState(),
    content: SettingScope.() -> Unit,
) {

    settingState.value.advanceIndex

    val map: MutableMap<Int, (@Composable () -> Unit)?> = mutableMapOf()

    val list: MutableList<@Composable () -> Unit> = mutableListOf()

    val settingScopeImpl = SettingScopeImpl(
        map = map,
        list = list,
        settingState
    )
    settingScopeImpl.content()

    if (settingState.value.isFistView) {
        LazyColumn {
            items(list) {
                it()
            }
        }
    } else {
        val contentFun = map[settingState.value.advanceIndex]
        if (contentFun != null) {
            contentFun()
        } else {
            LazyColumn {
                items(list) {
                    it()
                }
            }
        }
    }
}