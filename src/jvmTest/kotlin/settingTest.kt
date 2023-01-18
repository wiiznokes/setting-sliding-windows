import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.settingSlidingWindows.Setting
import com.example.settingSlidingWindows.rememberSettingState
import com.example.settingSlidingWindows.utils.getIcon


enum class Languages {
    EN,
    FR,
    DR,
    FL
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingTest(
    drawerState: DrawerState,
) {

    // current language used
    val language = remember {
        mutableStateOf(Languages.EN)
    }

    // entry point of the library
    Setting(
        settingState = rememberSettingState(key = drawerState.isOpen)
    ) {
        // display header (From DSL)
        header(
            title = "Setting"
        )

        // (From DSL)
        item(
            icon = {
                Icon(
                    painter = getIcon("settings/translate40"),
                    contentDescription = null
                )
            },
            title = "translate",
            subTitle = language.value.name,
            showTopLine = true
        ) {
            // content displayed when we click on the setting

            // displayed header (parameter optional because already know)
            // include a button to go back
            // (From DSL)
            Header(title = null, settingColors = null)

            // display a list of language
            LanguageAdvanceSetting(language)
        }

        // (From DSL)
        // display a subtitle to distinguish related items
        group("other")

        // (From DSL)
        item(
            content = {
                Text(
                    text = "setting1",
                    modifier = Modifier.padding(16.dp)
                )
            }
        )

        // (From DSL)
        item(
            content = {
                Text(
                    text = "setting2",
                    modifier = Modifier.padding(16.dp)
                )
            }
        )
    }
}

@Composable
private fun LanguageAdvanceSetting(
    language: MutableState<Languages>,
) {
    LazyColumn {
        items(Languages.values()) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            language.value = it
                        }
                ) {
                    Text(
                        text = it.toString(),
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    thickness = 2.dp
                )
            }
        }
        item {
            Spacer(Modifier.height(80.dp))
        }
    }
}
