package data.disciplines

import domain.device.Device
import domain.disciplines.ChooseDeviceDiscipline
import domain.model.ChooseDeviceResult

// TODO: 24.11.2021 add documentation 
class ChooseDeviceDisciplineByRing : ChooseDeviceDiscipline<Device> {

    private var lastChooseDevice: Device? = null

    // TODO: 24.11.2021 bad algorithm of choosing devide 
    override fun choose(devices: Array<Device>): ChooseDeviceResult<Device> {
        if (lastChooseDevice == null) {
            for (device in devices) {
                if (device.isFree()) {
                    lastChooseDevice = device
                    return ChooseDeviceResult.Success(device)
                }
            }
        } else {
            val index = devices.indexOf(lastChooseDevice) + 1
            if (index == devices.size) {
                for (i in devices.indices) {
                    if (devices[i].isFree()) {
                        lastChooseDevice = devices[i]
                        return ChooseDeviceResult.Success(devices[i])
                    }
                }
            }
            for (i in index..devices.lastIndex) {
                if (devices[i].isFree()) {
                    lastChooseDevice = devices[i]
                    return ChooseDeviceResult.Success(devices[i])
                }
            }
            for (i in 0 until index) {
                if (devices[i].isFree()) {
                    lastChooseDevice = devices[i]
                    return ChooseDeviceResult.Success(devices[i])
                }
            }
        }
        return ChooseDeviceResult.Error(IllegalStateException("Ошибка выбора прибора"))
    }

}