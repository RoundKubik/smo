package data.device

import domain.device.Device
import domain.device.DeviceController
import domain.disciplines.ChooseDeviceDiscipline
import domain.model.ChooseDeviceResult
import domain.model.Request

class DeviceControllerImpl(
    private val deviceCount: Int,
    private val lambda: Double,
    private val chooseDeviceDiscipline: ChooseDeviceDiscipline<Device>
) : DeviceController<Device> {

    private var devices: Array<Device> = Array(deviceCount) {
        Device(
            it + 1,
            lambda
        )
    }


    override fun chooseDevice(): Device? {
        when (val deviceResult = chooseDeviceDiscipline.choose(devices)) {
            is ChooseDeviceResult.Success -> {
                return deviceResult.data
            }
        }
        return null
    }

    override fun getDevices(): Array<Device> {
        return devices
    }

    override fun setDevice(request: Request) {
        chooseDevice()?.let { choose ->
            devices.find {
                it == choose
            }?.startWork(request)
        }
    }

    override fun allWork(): Boolean {
        devices.forEach {
            if (it.isFree()) {
                return false
            }
        }
        return true
    }

    override fun notifyByTime(array: Array<Request>) {
/*
         if (array.isEmpty()) {
             finishDevicesByMinTime(Double.MAX_VALUE)
             return
         }
*/
        var minTime = Double.MAX_VALUE
        array.forEach {
            if (it.generationTime < minTime) {
                minTime = it.generationTime
            }
        }
        finishDevicesByMinTime(minTime)
    }

    private fun finishDevicesByMinTime(minTime: Double) {
        var minDeviceTime = Double.MAX_VALUE
        devices.forEach {
            if (minDeviceTime > it.lastProcessedRequest.processedTime) {
                minDeviceTime = it.lastProcessedRequest.processedTime
            }
        }
        if ((minDeviceTime < minTime) or (minTime == 0.0)) {
            devices.find { it.lastProcessedRequest.processedTime == minDeviceTime }?.finishWork()
        }
    }

    override fun clearDevices() {
        devices = Array(deviceCount) {
            Device()
        }
    }

}