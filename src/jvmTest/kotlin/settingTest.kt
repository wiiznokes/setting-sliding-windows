import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.settingSlidingWindows.Setting
import com.example.settingSlidingWindows.rememberSettingState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun settingTest(
    drawerState: DrawerState
) {
    val settingState = rememberSettingState(
        key = drawerState.isOpen
    )

    Setting(settingState) {
        topSetting(
            title = "Setting"
        )

        item(
            icon = {
                Icon(
                    painter = getIcon("settings/translate40"),
                    contentDescription = null
                )
            },
            title = mutableStateOf("translate"),
            subTitle = mutableStateOf("app translation")
        ) {
            advanceItem {
                item {
                    Text("hello")
                }
            }
        }

        item(
            icon = {
                Icon(
                    painter = getIcon("settings/help40"),
                    contentDescription = null
                )
            },
            title = mutableStateOf("help"),
            subTitle = mutableStateOf("get help here")
        ) {
            advanceItem {
                item {
                    Text("hello")
                }
            }
        }
    }
}