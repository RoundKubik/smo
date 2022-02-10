package domain.buffer_controller

import domain.buffer.Buffer

/**
 * [R] - request type
 * [T] - buffer type
 */
interface BufferController<R, out T : Buffer<R>> {
    fun fillRequest(request: R)
    fun getRequest() :R
    fun isFree() : Boolean
    fun hasElements() : Boolean
    fun getBufferElements() : Array<R>
}