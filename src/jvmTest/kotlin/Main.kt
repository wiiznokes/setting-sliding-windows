import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.settingSlidingWindows.setting


fun main () = application {

    Window(
        onCloseRequest = {
            exitApplication()
        }
    ) {
        setting {
            topSetting(
                title = "hello"
            )
            item(
                icon = {},
                title = mutableStateOf("title"),
                subTitle = mutableStateOf("title"),
                sliderIcon = {}
            ) {
                advanceItem(
                    sliderIcon = {}
                ) {
                    item {
                        Text("bonjour")
                    }
                }
            }
        }
    }
}