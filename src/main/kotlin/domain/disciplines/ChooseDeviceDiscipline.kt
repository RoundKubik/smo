package domain.disciplines

import domain.model.ChooseDeviceResult

interface ChooseDeviceDiscipline<T> {
    fun choose(devices: Array<T>): ChooseDeviceResult<T>
}