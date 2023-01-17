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
    private val settingState: MutableState<SettingState>,
    private val index: Int,
    private val title: String? = null,
) : AdvanceSettingScope {


    override fun advanceItem(
        content: LazyListScope.() -> Unit,
    ) {
        map[index] = {
            baseAdvanceItem(
                content = content
            )
        }
    }


    override fun advanceItemCustom(
        content: @Composable () -> Unit,
    ) {
        map[index] = content
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun baseAdvanceItem(
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
                            onClick = {
                                settingState.value = settingState.value.copy(
                                    isFistView = true
                                )
                                println("slider icon click -> isFistView = true")
                            }
                        ) {
                            Icon(
                                painter = getIcon("chevron/chevron_left40"),
                                contentDescription = null
                            )
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