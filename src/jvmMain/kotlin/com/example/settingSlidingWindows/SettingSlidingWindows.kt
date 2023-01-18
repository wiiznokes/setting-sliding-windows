package com.example.settingSlidingWindows

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


internal class SettingException(msg: String) : Exception(msg)


/**
 * Defaults value of Setting
 */
object SettingDefaults {

    /**
     * Default colors value for Setting
     */
    @Composable
    fun settingColors(
        container: Color = MaterialTheme.colorScheme.surface,
        onContainer: Color = MaterialTheme.colorScheme.onSurface,
    ): SettingColors = SettingColors(
        container = container,
        onContainer = onContainer
    )
}


data class SettingColors(
    val container: Color,
    val onContainer: Color,
)


data class SettingValue(
    val isFirstView: Boolean = true,
    val advanceIndex: Int = 0,
    val scrollState: Int = 0
)


class SettingState(
    initialValue: SettingValue,
) {

    internal val lazyListState = LazyListState(initialValue.scrollState)

    private val currentValue = mutableStateOf(initialValue)
    val isFirstView: Boolean
        get() = currentValue.value.isFirstView

    val advanceIndex: Int
        get() = currentValue.value.advanceIndex


    /**
     * close advance and return to the first one
     */
    fun close() {
        currentValue.value = currentValue.value.copy(
            isFirstView = true
        )
    }

    /**
     * display advance setting of the corresponding index
     * from [advanceIndex]. If there is no advance setting,
     * the first view will be shown
     */
    fun openAdvance(int: Int) {
        currentValue.value = currentValue.value.copy(
            advanceIndex = int,
            isFirstView = false
        )
    }

}


/**
 * remember [SettingState] for you.
 * @param initialValue Initial value of [SettingState]
 * @param key Key used in remember, to know when recalculate the value
 */
@Composable
fun rememberSettingState(
    initialValue: SettingValue = SettingValue(),
    key: Any = Unit,
): SettingState {
    return remember(key) {
        SettingState(initialValue)
    }
}


/**
 * Entry point of this library. Display a list of setting,
 * declared in content params. A DSL [SettingScope] is provided,
 * and provide premake function that you can use to construct
 * a beautiful setting sliding windows !
 * @param modifier Modifier of the LazyList where items are
 * displayed
 * @param settingState State of setting, used to make custom items
 * @param settingColors Colors of setting
 * @param content DSL (Domain Specific Language
 */
@Composable
fun Setting(
    modifier: Modifier = Modifier,
    settingState: SettingState = rememberSettingState(),
    settingColors: SettingColors = SettingDefaults.settingColors(),
    content: SettingScope.() -> Unit,
) {

    val map: MutableMap<Int, (@Composable () -> Unit)?> = mutableMapOf()
    val list: MutableList<@Composable () -> Unit> = mutableListOf()


    val settingScopeImpl = SettingScopeImpl(
        map = map,
        list = list,
        settingState = settingState,
        _settingColors = settingColors
    )
    settingScopeImpl.content()

    if (settingState.isFirstView) {
        BaseFirstView(
            modifier = modifier,
            settingState = settingState,
            list = list
        )
    } else {
        val contentFun = map[settingState.advanceIndex]
        if (contentFun != null) {
            contentFun()
        } else {
            BaseFirstView(
                modifier = modifier,
                settingState = settingState,
                list = list
            )
        }
    }
}

@Composable
private fun BaseFirstView(
    modifier: Modifier = Modifier,
    settingState: SettingState = rememberSettingState(),
    list: MutableList<@Composable () -> Unit>
) {
    LazyColumn(
        modifier = modifier,
        state = settingState.lazyListState
    ) {
        items(list) {
            it()
        }
        item {
            Spacer(Modifier.height(80.dp))
        }
    }
}