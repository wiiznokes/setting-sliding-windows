package com.example.settingSlidingWindows

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


private val map: MutableMap<Int, (() -> Unit)?> = mutableMapOf()

private val list: MutableList<@Composable () -> Unit> = mutableListOf()

@Composable
fun setting(
    content: SettingScope.() -> Unit,
) {

    val isFistView = remember { mutableStateOf(true) }
    val currentIndex = remember { mutableStateOf(0) }

    val settingScopeImpl = SettingScopeImpl(
        map = map,
        list = list,
        isFistView = isFistView,
        currentIndex = currentIndex
    )
    settingScopeImpl.content()

    println("bisous")
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