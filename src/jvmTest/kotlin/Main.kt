import androidx.compose.foundation.background
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            modifier = Modifier.background(Color.Cyan),
            drawerContent = {
                settingTest(
                    drawerState = drawerState
                )
            },
            drawerState = drawerState,
        ) {

        }
    }
}