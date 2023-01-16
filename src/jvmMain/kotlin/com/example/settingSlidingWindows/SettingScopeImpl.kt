package com.example.settingSlidingWindows

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class SettingScopeImpl: SettingScope {


    private val isFistView = mutableStateOf(true)
    private var size = 0
    private val currentIndex = mutableStateOf(0)


    @Composable
    override fun item(
        icon: @Composable (() -> Unit)?,
        title: MutableState<String>,
        subTitle: MutableState<String>?,
        sliderIcon: @Composable () -> Unit,
        advanceItemContent: @Composable AdvanceSettingScope.() -> Unit
    ) {
        val index = size
        size++

        val advanceSettingScopeImpl = AdvanceSettingScopeImpl(
            currentIndex = currentIndex,
            isFistView = isFistView,
            index = index
        )
        advanceSettingScopeImpl.advanceItemContent()


            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                Row(
                    Modifier
                        .clickable(onClick = { currentIndex.value = index }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

                        IconButton(
                            onClick = { currentIndex.value = index }
                        ) {
                            sliderIcon()
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

            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp
            )
        }
    }

    @Composable
    override fun item(content: @Composable () -> Unit) {
        TODO("Not yet implemented")
    }

    @Composable
    override fun topSetting(title: String) {
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

    @Composable
    override fun topSetting(content: @Composable () -> Unit) {
        TODO("Not yet implemented")
    }

}