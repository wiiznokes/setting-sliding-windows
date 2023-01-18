import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


@OptIn(ExperimentalMaterial3Api::class)
fun main() = application {

    Window(
        onCloseRequest = {
            exitApplication()
        }
    ) {

        val drawerState = rememberDrawerState(DrawerValue.Closed)

        ModalNavigationDrawer(
            drawerContent = {
                SettingTest(
                    drawerState = drawerState
                )
            },
            drawerState = drawerState,
        ) {

        }
    }
}