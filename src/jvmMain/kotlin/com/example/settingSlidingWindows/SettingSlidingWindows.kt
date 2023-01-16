package com.example.settingSlidingWindows

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


private val settingScopeImpl = SettingScopeImpl()
@Composable
fun setting(
    content: @Composable SettingScope.() -> Unit
) {
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