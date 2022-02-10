package presentation.main_screen

sealed class MainScreenTabs(val title: String) {
    object Settings: MainScreenTabs("Settings")
    object AutoMode: MainScreenTabs("AutoMode")
    object StepMode: MainScreenTabs("StepMode")
}