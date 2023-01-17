import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.settingSlidingWindows.Setting
import com.example.settingSlidingWindows.rememberSettingState

@Suppress("EnumEntryName")
enum class Languages {
    en,
    fr;

    companion object {
        infix fun from(value: String): Languages = Languages.values().first { it.name == value }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun settingTest(
    drawerState: DrawerState
) {


    val language = remember {
        mutableStateOf(Languages.en.name)
    }

    Setting {
        header(
            title = "Setting"
        )

        item(
            icon = {
                Icon(
                    painter = getIcon("settings/translate40"),
                    contentDescription = null
                )
            },
            title = "translate",
            subTitle = language.value
        ) {

            header(null, null)


            LazyColumn {

                items(Languages.values()) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    language.value = it.toString()
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
    }
}