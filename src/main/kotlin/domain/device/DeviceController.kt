package domain.device

import domain.model.Request

interface DeviceController<T> {
    fun chooseDevice(): T?
    fun setDevice(request: Request)
    fun getDevices(): Array<T>
    fun allWork(): Boolean
    fun notifyByTime(array: Array<Request>)
    fun clearDevices()
}