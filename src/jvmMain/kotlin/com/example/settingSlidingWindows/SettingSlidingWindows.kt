package com.example.settingSlidingWindows

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.settingSlidingWindows.firstView.SettingScope
import com.example.settingSlidingWindows.firstView.SettingScopeImpl



object SettingDefaults {

    @Composable
    fun settingColors(
        container: Color = MaterialTheme.colorScheme.surface,
        onContainer: Color = MaterialTheme.colorScheme.onSurface
    ): SettingColors = SettingColors(
        container = container,
        onContainer = onContainer
    )
}


data class SettingColors(
    val container: Color,
    val onContainer: Color
)



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
    modifier: Modifier = Modifier,
    settingState: MutableState<SettingState> = rememberSettingState(),
    settingColors: SettingColors = SettingDefaults.settingColors(),
    content: SettingScope.() -> Unit,
) {

    settingState.value.advanceIndex

    val map: MutableMap<Int, (@Composable () -> Unit)?> = mutableMapOf()

    val list: MutableList<@Composable () -> Unit> = mutableListOf()

    val settingScopeImpl = SettingScopeImpl(
        map = map,
        list = list,
        settingState = settingState,
        settingColors = settingColors
    )
    settingScopeImpl.content()

    if (settingState.value.isFistView) {
        LazyColumn(
            modifier = modifier
        ) {
            items(list) {
                it()
            }
        }
    } else {
        val contentFun = map[settingState.value.advanceIndex]
        if (contentFun != null) {
            contentFun()
        } else {
            LazyColumn(
                modifier = modifier
            ) {
                items(list) {
                    it()
                }
            }
        }
    }
}