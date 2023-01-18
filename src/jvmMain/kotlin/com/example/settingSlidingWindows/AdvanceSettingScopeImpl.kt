package com.example.settingSlidingWindows

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.settingSlidingWindows.utils.SettingTypo
import com.example.settingSlidingWindows.utils.getIcon


internal class AdvanceSettingScopeImpl(
    private val settingState: SettingState,
    private val _settingColors: SettingColors,
    private val _title: String? = null,
) : AdvanceSettingScope {

    @Composable
    override fun Header(
        title: String?,
        settingColors: SettingColors?,
    ) {

        if (_title == null && title == null)
            throw SettingException(
                "title has to be passed in argument " +
                        "if it hasn't be defined inside SettingScope"
            )

        if (title != null)
            BaseHeader(
                title = title,
                settingColors = settingColors ?: _settingColors
            )
        else
            BaseHeader(
                title = _title!!,
                settingColors = settingColors ?: _settingColors
            )

    }


    @Composable
    private fun BaseHeader(
        title: String,
        settingColors: SettingColors,
    ) {
        Column(
            modifier = Modifier.background(
                color = settingColors.container
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
            ) {
                IconButton(
                    onClick = {
                        settingState.close()
                    }
                ) {
                    Icon(
                        painter = getIcon("chevron/chevron_left40"),
                        contentDescription = null,
                        tint = settingColors.onContainer
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    style = SettingTypo.titleMedium,
                    color = settingColors.onContainer,
                    text = title
                )
            }

            Divider(
                modifier = Modifier.fillMaxWidth(0.95f),
                thickness = 2.dp,
                color = settingColors.onContainer
            )
            Spacer(Modifier.height(40.dp))
        }
    }

}