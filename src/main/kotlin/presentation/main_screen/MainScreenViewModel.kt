package presentation.main_screen

import com.sun.tools.javac.Main
import kotlinx.coroutines.flow.MutableStateFlow

class MainScreenViewModel {
    var screenState: MutableStateFlow<MainScreenTabs> = MutableStateFlow(MainScreenTabs.Settings)

    fun openScreen(item: MainScreenTabs) {
        screenState.value = item
    }
}