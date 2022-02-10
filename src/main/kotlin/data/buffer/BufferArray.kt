package data.buffer

import domain.buffer.Buffer
import domain.model.Request

/**
 * [add] - insert in first free place, implement this method
 * Д1033 - Added discipline of buffering by first free space in buffer
 *
 * Дисциплина отказа, отказ той заявке у которой номер источника больше
 * Дисциплина отказа, наиболее старой заявке, у которой номер источника одинаков
 *
 */
class BufferArray(
    private val size: Int
) : Buffer<Request> {

    private var elements = Array(size) {
        Request()
    }

    override fun get(): Request {
        return Request()
    }

    override fun getElements(): Array<Request> {
        return elements
    }

    override fun update(requests: Array<Request>) {
        elements = requests

    }


}