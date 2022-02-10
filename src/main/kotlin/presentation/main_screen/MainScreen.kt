package presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import presentation.auto_mode.AutoModeScreen
import presentation.settings.SettingsScreen
import presentation.step_mode.StepModeScreen

@Composable
fun MainScreen(viewModel: MainScreenViewModel = MainScreenViewModel()) {
    val tabs = listOf(
        MainScreenTabs.Settings,
        MainScreenTabs.AutoMode,
        MainScreenTabs.StepMode
    )
    val state = viewModel.screenState.collectAsState()

    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(Color.Blue).fillMaxWidth()
        ) {
            ButtonTabsScreen(tabs, viewModel)
        }
        OpenScreenByState(state.value)
    }



}

@Composable
fun ButtonTabsScreen(tabs: List<MainScreenTabs>, viewModel: MainScreenViewModel) {

    Row {
        tabs.forEach {
            Button(
                onClick = {
                    viewModel.openScreen(it)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = it.title
                )
            }
        }
    }
}

@Composable
fun OpenScreenByState(item: MainScreenTabs) {
    when(item) {
        is MainScreenTabs.StepMode -> {
            StepModeScreen()
        }
        is MainScreenTabs.AutoMode -> {
            AutoModeScreen()
        }
        is MainScreenTabs.Settings -> {
            SettingsScreen()
        }
    }

}
