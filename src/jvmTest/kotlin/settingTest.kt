import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.example.settingSlidingWindows.setting


@Composable
fun settingTest() {
    setting {
        topSetting(
            title = "hello"
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