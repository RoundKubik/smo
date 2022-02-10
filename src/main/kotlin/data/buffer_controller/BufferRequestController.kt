package data.buffer_controller

import domain.disciplines.BufferingDiscipline
import domain.model.BufferingResult
import domain.model.Request
import domain.buffer.Buffer
import domain.buffer_controller.BufferController
import domain.disciplines.ChooseRequestDiscipline
import domain.model.ChooseRequestResult
import domain.disciplines.RejectDiscipline
import presentation.Analytics


// TODO: 25.11.2021 implement as view model in android
class BufferRequestController(
    private val buffer: Buffer<Request>,
    private val bufferingDiscipline: BufferingDiscipline<Request>,
    private val rejectDiscipline: RejectDiscipline<Request>,
    private val chooseRequestDiscipline: ChooseRequestDiscipline<Request>
) : BufferController<Request, Buffer<Request>> {

    override fun fillRequest(request: Request) {
        when (val bufferingResult = bufferingDiscipline.buffering(request, elements = buffer.getElements())) {
            is BufferingResult.Success -> buffer.update(bufferingResult.data)
            is BufferingResult.OverFlow -> {
                Analytics.rejectedTasksCount++
                val rejectResult = rejectDiscipline.provideReject(
                    request,
                    buffer.getElements()
                )
                buffer.update(rejectResult)
            }
        }
    }

    override fun getRequest(): Request {
        return chooseRequestAndGet()
    }

    private fun chooseRequestAndGet(): Request {
        val bufferElements = buffer.getElements()
        when (val chooseRequest = chooseRequestDiscipline.choose(bufferElements)) {
            is ChooseRequestResult.Success -> {
                val indexRemovedElem = bufferElements.indexOf(chooseRequest.data)
                if (indexRemovedElem != -1) {
                    bufferElements[indexRemovedElem] = Request()
                }
                buffer.update(bufferElements)
                return chooseRequest.data
            }
            is ChooseRequestResult.Error -> {
                throw IllegalArgumentException("Ошибка выбора запроса")
            }
        }
    }

    override fun isFree(): Boolean {
        return buffer.getElements().count {
            it != Request()
        } != buffer.getElements().size
    }

    override fun getBufferElements(): Array<Request> {
        return buffer.getElements()
    }

    override fun hasElements(): Boolean {
        buffer.getElements().forEach {
            if (it != Request()) {
                return true
            }
        }
        return false
    }

}