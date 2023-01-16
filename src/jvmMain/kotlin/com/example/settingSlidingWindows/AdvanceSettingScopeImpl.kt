package com.example.settingSlidingWindows

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class AdvanceSettingScopeImpl(
    private val map: MutableMap<Int, (@Composable () -> Unit)?>,
    private val currentIndex: MutableState<Int>,
    private val isFistView: MutableState<Boolean>,
    private val index: Int,
    private val title: String? = null,
) : AdvanceSettingScope {


    override fun advanceItem(
        sliderIcon: @Composable () -> Unit,
        content: LazyListScope.() -> Unit,
    ) {
        map[index] = {
            baseAdvanceItem(
                sliderIcon = sliderIcon,
                content = content
            )
        }
    }


    override fun advanceItem(
        content: @Composable () -> Unit,
    ) {
        map[index] = content
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun baseAdvanceItem(
        sliderIcon: @Composable () -> Unit,
        content: LazyListScope.() -> Unit,
    ) {
        LazyColumn {
            stickyHeader {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    ) {
                        IconButton(
                            onClick = { currentIndex.value = 0 }
                        ) {
                            sliderIcon()
                        }
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = title!!
                        )
                    }

                    Divider(
                        modifier = Modifier.fillMaxWidth(0.95f),
                        thickness = 2.dp
                    )
                    Spacer(Modifier.height(40.dp))
                }
            }
            content()
        }
    }
}