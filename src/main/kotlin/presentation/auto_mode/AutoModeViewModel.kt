package presentation.auto_mode

import data.buffer.BufferArray
import data.buffer_controller.BufferRequestController
import data.device.DeviceControllerImpl
import data.disciplines.BufferingDisciplineImpl
import data.disciplines.ChooseDeviceDisciplineByRing
import data.disciplines.ChooseRequestDisciplineImpl
import data.disciplines.RejectDisciplineBySourcePriority
import data.source.SourceControllerImpl
import domain.buffer.Buffer
import domain.buffer_controller.BufferController
import domain.device.Device
import domain.device.DeviceController
import domain.model.Request
import domain.source.Source
import domain.source.SourceController
import kotlinx.coroutines.flow.MutableStateFlow
import presentation.Analytics
import presentation.model.AutoModeDevice
import presentation.model.AutoModeSource
import java.util.*

class AutoModeViewModel constructor(
    private val bufferController: BufferController<Request, Buffer<Request>> = BufferRequestController(
        BufferArray(Config.BUFFER_SIZE),
        BufferingDisciplineImpl(),
        RejectDisciplineBySourcePriority(),
        ChooseRequestDisciplineImpl()
    ),
    private val deviceController: DeviceController<Device> = DeviceControllerImpl(
        Config.DEVICES_COUNT,
        Config.lambda,
        ChooseDeviceDisciplineByRing()
    ),
    private val sourceController: SourceController<Source> = SourceControllerImpl(
        Config.SOURCES_COUNT,
        Config.REQUEST_COUNT,
        Config.a,
        Config.b
    )
) {

    var autoModeStatistics = MutableStateFlow<List<AutoModeSource>>(
        listOf()
    )

    val deviceStatistics = MutableStateFlow<List<AutoModeDevice>>(
        listOf(
        )
    )

    fun getSourceStatistics(): List<AutoModeSource> {
        return listOf()
    }

    fun start() {
       while (true) {
            nextStep()
            Thread.sleep(100)
       }
    }

    private fun nextStep() {
        if (!deviceController.allWork() and bufferController.isFree() and !sourceController.isEndRequests()) {
            deviceController.setDevice(sourceController.getNextRequest())
        } else if (!deviceController.allWork() and bufferController.hasElements()) {
            deviceController.setDevice(bufferController.getRequest())
        } else if (deviceController.allWork()) {
            if (!sourceController.isEndRequests()) {
                bufferController.fillRequest(sourceController.getNextRequest())
            } else {
                deviceController.setDevice(bufferController.getRequest())
            }
        }

        deviceController.notifyByTime(sourceController.getRequestsForStatistic())
        updateStatistics()
    }
    fun updateStatistics() {
        autoModeStatistics.value = listOf(
            AutoModeSource(
                "1",
                Analytics.deviceRequestCount[0],
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0
            )
        )
    }
}