package com.example.settingSlidingWindows

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember




@Composable
fun setting(
    content: SettingScope.() -> Unit,
) {
    val map: MutableMap<Int, (@Composable () -> Unit)?> = mutableMapOf()

    val list: MutableList<@Composable () -> Unit> = mutableListOf()

    val isFistView = remember { mutableStateOf(true) }
    val currentIndex = remember { mutableStateOf(0) }

    val settingScopeImpl = SettingScopeImpl(
        map = map,
        list = list,
        isFistView = isFistView,
        currentIndex = currentIndex
    )
    settingScopeImpl.content()

    if (isFistView.value) {
        LazyColumn {
            items(list) {
                it()
            }
        }
    } else {
        val contentFun = map[currentIndex.value]
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