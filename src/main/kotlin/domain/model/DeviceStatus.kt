package domain.model

sealed class DeviceStatus {

    object Free: DeviceStatus()
    object InProgress: DeviceStatus()

    override fun toString(): String = when(this) {
        is Free -> "Свободен"
        is InProgress -> "Занят"
    }
}
