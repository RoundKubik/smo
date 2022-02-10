package presentation.settings

import Config
import presentation.model.Settings

class SettingsViewModel {

    fun saveSettings(settings: Settings) {
        with(Config) {
            SOURCES_COUNT = settings.countSources
            DEVICES_COUNT = settings.countDevices
            BUFFER_SIZE = settings.bufferSize
            REQUEST_COUNT = settings.requestCount
            a = settings.a
            b = settings.b
            lambda = settings.lambda
        }

    }
}