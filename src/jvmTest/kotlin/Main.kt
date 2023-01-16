import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
                icon = {
                    Icon(
                        painter = getIcon("settings/translate40"),
                        contentDescription = null
                    )
                },
                title = mutableStateOf("translate"),
                subTitle = mutableStateOf("app translation"),
                sliderIcon = {
                    Icon(
                        painter = getIcon("chevron/chevron_right40"),
                        contentDescription = null
                    )
                }
            ) {
                advanceItem(
                    sliderIcon = {
                        Icon(
                            painter = getIcon("chevron/chevron_left40"),
                            contentDescription = null
                        )
                    }
                ) {
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
                subTitle = mutableStateOf("get help here"),
                sliderIcon = {
                    Icon(
                        painter = getIcon("chevron/chevron_right40"),
                        contentDescription = null
                    )
                }
            ) {
                advanceItem(
                    sliderIcon = {
                        Icon(
                            painter = getIcon("chevron/chevron_left40"),
                            contentDescription = null
                        )
                    }
                ) {
                    item {
                        Text("hello")
                    }
                }
            }
        }
    }
}