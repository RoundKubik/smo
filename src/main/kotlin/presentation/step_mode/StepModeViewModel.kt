package presentation.step_mode

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
import presentation.model.BufferStatistics
import presentation.model.EventCalendarStatistics

class StepModeViewModel(
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


    var bufferStatistics =
        MutableStateFlow<List<BufferStatistics>>(bufferController.getBufferElements().mapIndexed { index, request ->
            BufferStatistics(
                index.toString(),
                request.generationTime,
                0,
                0
            )
        })

    var eventDeviceStatistics = MutableStateFlow<List<EventCalendarStatistics>>(
        deviceController.getDevices().mapIndexed { index, it ->
            EventCalendarStatistics(
                "П${index + 1}",
                it.lastProcessedRequest.processedTime.toString(),
                it.status.toString(),
                "${it.lastProcessedRequest.sourceNum}, ${it.lastProcessedRequest.requestNum + 1}"
            )
        }
    )
    var eventSourceStatistics =
        MutableStateFlow<List<EventCalendarStatistics>>(sourceController.getRequestsForStatistic().mapIndexed { index, it ->
            EventCalendarStatistics(
                "И${index + 1}",
                it.generationTime.toString(),
                if (it.requestNum> -1.0) "Работает" else "Отключено",
                "${it.sourceNum}.${it.requestNum + 1}"
            )
        })
    var eventEndEmulationStatistics = MutableStateFlow<List<EventCalendarStatistics>>(
        listOf()
    )

    fun nextStep() {

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

    private fun updateStatistics() {
        val requests = sourceController.getRequestsForStatistic()
        eventSourceStatistics.value = requests.mapIndexed { index, it ->
            EventCalendarStatistics(
                "И${index + 1}",
                it.generationTime.toString(),
                if (it.requestNum> -1.0) "Работает" else "Отключено",
                "${it.sourceNum}.${it.requestNum + 1}"
            )
        }

        bufferStatistics.value = bufferController.getBufferElements().mapIndexed { index, request ->
            BufferStatistics(
                index.toString(),
                request.generationTime,
                if (request.sourceNum == -1) 0 else request.sourceNum,
                if (request.requestNum == -1) 0 else request.requestNum
            )
        }

        eventDeviceStatistics.value =
            deviceController.getDevices().mapIndexed { index, it ->
                EventCalendarStatistics(
                    "П${index + 1}",
                    it.lastProcessedRequest.processedTime.toString(),
                    it.status.toString(),
                    "${it.lastProcessedRequest.sourceNum}, ${it.lastProcessedRequest.requestNum + 1}"
                )
            }


    }
}