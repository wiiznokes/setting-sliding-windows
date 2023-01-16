package com.example.settingSlidingWindows

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf


private val settingScopeImpl = SettingScopeImpl()


private val isFistView = mutableStateOf(true)
private val currentIndex = mutableStateOf(0)


private val map = mapOf<Int, AdvanceSettingScope?>()

private val list: MutableList<SettingScope> = mutableListOf()

@Composable
fun setting(
    content: @Composable SettingScope.() -> Unit
) {
    listOf(content)

    if (isFistView.value)
    Column {
        settingScopeImpl.content()
    }

}


@Composable
fun test() {
    setting {
        topSetting(
            title = "hello"
        )
        item(
            icon =  {},
            title = mutableStateOf("title"),
            subTitle = mutableStateOf("title"),
            sliderIcon = {}
        ) {
            advanceItem(
                sliderIcon = {}
            ) {
                Text("bonjour")
            }
        }
    }
}